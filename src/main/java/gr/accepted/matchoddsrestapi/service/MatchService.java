package gr.accepted.matchoddsrestapi.service;

import gr.accepted.matchoddsrestapi.exception.EntityNotFoundException;
import gr.accepted.matchoddsrestapi.exception.IdMismatchException;
import gr.accepted.matchoddsrestapi.model.dto.request.CreateMatchRequest;
import gr.accepted.matchoddsrestapi.model.dto.request.UpdateMatchRequest;
import gr.accepted.matchoddsrestapi.model.dto.request.UpdateNestedMatchOddRequest;
import gr.accepted.matchoddsrestapi.model.dto.response.AllMatchesResponse;
import gr.accepted.matchoddsrestapi.model.dto.response.MatchResponse;
import gr.accepted.matchoddsrestapi.model.dto.response.MatchWithDetailsResponse;
import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import gr.accepted.matchoddsrestapi.repository.MatchRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public AllMatchesResponse<?> getAllMatches(Boolean withDetails) {
        return new AllMatchesResponse<>(matchRepository.findAll().stream()
                .map((match) -> withDetails ?
                        new MatchWithDetailsResponse(match) :
                        new MatchResponse(match)).toList());
    }

    public MatchWithDetailsResponse getMatchById(Long id) {
        return matchRepository.findById(id).map(MatchWithDetailsResponse::new)
                .orElseThrow(() -> new EntityNotFoundException("Match with id " + id + " does not exist"));
    }

    public Long saveNewMatch(@RequestBody CreateMatchRequest matchRequest) {
        return matchRepository.save(matchRequest.toEntity()).getId();
    }

    public void updateMatch(UpdateMatchRequest matchRequest, Long id) {
        // Check if matchId in path and body are the same
        if (matchRequest.getId() != null && !matchRequest.getId().equals(id)) {
            throw new IdMismatchException("Id in path and body not consistent");
        }

        // Check if match to be updated with given matchId exists
        Match previous = matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match with id " + id + " does not exist"));

        // Check if incoming matchOddIds exist and refer to the match to be updated
        List<Long> existingMatchOddIds = previous.getMatchOdds().stream().map(MatchOdd::getId).toList();
        List<Long> incomingMatchOddIds = matchRequest.getMatchOdds().stream().map(UpdateNestedMatchOddRequest::getId).filter(Objects::nonNull).toList();
        for (Long incomingId : incomingMatchOddIds) {
            if (!existingMatchOddIds.contains(incomingId)) {
                throw new IdMismatchException("Match odd id non-existent or referring to different match");
            }
        }

        matchRequest.setId(id);
        matchRepository.save(matchRequest.toEntity());
    }

    public void deleteMatch(Long id) {
        matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match with id " + id + " does not exist"));
        matchRepository.deleteById(id);
    }
}
