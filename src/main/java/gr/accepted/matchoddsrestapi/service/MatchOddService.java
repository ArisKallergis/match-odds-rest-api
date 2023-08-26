package gr.accepted.matchoddsrestapi.service;


import gr.accepted.matchoddsrestapi.exception.EntityNotFoundException;
import gr.accepted.matchoddsrestapi.exception.IdMismatchException;
import gr.accepted.matchoddsrestapi.model.dto.request.CreateMatchOddRequest;
import gr.accepted.matchoddsrestapi.model.dto.request.UpdateMatchOddRequest;
import gr.accepted.matchoddsrestapi.model.dto.response.AllMatchOddsResponse;
import gr.accepted.matchoddsrestapi.model.dto.response.MatchOddResponse;
import gr.accepted.matchoddsrestapi.repository.MatchOddRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchOddService {

    private final MatchOddRepository matchOddRepository;

    public MatchOddService(MatchOddRepository matchOddRepository) {
        this.matchOddRepository = matchOddRepository;
    }

    public AllMatchOddsResponse getAllMatchOdds() {
        return new AllMatchOddsResponse(matchOddRepository.findAll().stream().map(MatchOddResponse::new).toList());
    }

    public MatchOddResponse getMatchOddById(Long id) {
        return matchOddRepository.findById(id).map(MatchOddResponse::new).orElseThrow(() -> new EntityNotFoundException("Match odd with id " + id + " does not exist"));
    }

    public Long saveNewMatchOdd(CreateMatchOddRequest matchOddRequest) {
        return matchOddRepository.save(matchOddRequest.toEntity()).getId();
    }

    public void updateMatchOdd(UpdateMatchOddRequest matchOddRequest, Long id) {
        if (matchOddRequest.getId() != null && !matchOddRequest.getId().equals(id)) {
            throw new IdMismatchException("Id in path and both not consistent");
        }
        matchOddRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match odd with id " + id + " does not exist"));

        matchOddRequest.setId(id);
        matchOddRepository.save(matchOddRequest.toEntity());
    }

    public void deleteMatchOdd(Long id) {
        matchOddRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Match odd with id " + id + " does not exist"));
        matchOddRepository.deleteById(id);
    }
}
