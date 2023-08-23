package gr.accepted.matchoddsrestapi.service;


import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import gr.accepted.matchoddsrestapi.repository.MatchOddRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchOddService {

    private final MatchOddRepository matchOddRepository;

    public MatchOddService(MatchOddRepository matchOddRepository) {
        this.matchOddRepository = matchOddRepository;
    }

    public MatchOdd saveSomething() {
        MatchOdd matchOdd = new MatchOdd("Some name");
        matchOddRepository.save(matchOdd);
        return matchOdd;
    }
}
