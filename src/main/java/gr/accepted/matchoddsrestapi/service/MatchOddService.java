package gr.accepted.matchoddsrestapi.service;


import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import gr.accepted.matchoddsrestapi.model.response.AllMatchOddsResponse;
import gr.accepted.matchoddsrestapi.repository.MatchOddRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchOddService {

    private final MatchOddRepository matchOddRepository;

    public MatchOddService(MatchOddRepository matchOddRepository) {
        this.matchOddRepository = matchOddRepository;
    }

    public List<MatchOdd> getAllMatchOdds() {
        return matchOddRepository.findAll();
    }
}
