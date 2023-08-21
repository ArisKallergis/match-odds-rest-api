package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.model.Match;
import gr.accepted.matchoddsrestapi.service.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "matches", consumes = "application/json", produces = "application/json")
public class MatchOddsController {

    private final MatchService matchService;

    public MatchOddsController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    Match getMatches() {
        return  matchService.saveSomething();
    }
}
