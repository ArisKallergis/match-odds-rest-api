package gr.accepted.matchoddsrestapi.service;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.response.AllMatchesResponse;
import gr.accepted.matchoddsrestapi.model.response.MatchResponse;
import gr.accepted.matchoddsrestapi.repository.MatchRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public AllMatchesResponse getAllMatches() {
        return new AllMatchesResponse(matchRepository.findAll());
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow();
    }

    public void saveNewMatch(Match match) {
        if (match.getId() != null) {
            match.setId(null);
        }
        matchRepository.save(match);
    }
}
