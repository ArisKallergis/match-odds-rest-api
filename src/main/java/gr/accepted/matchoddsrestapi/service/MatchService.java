package gr.accepted.matchoddsrestapi.service;

import gr.accepted.matchoddsrestapi.model.Match;
import gr.accepted.matchoddsrestapi.repository.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match saveSomething(){
        Match match = new Match("Some name");
        matchRepository.save(match);
        return match;
    }
}
