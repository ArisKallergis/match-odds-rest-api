package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.response.AllMatchesResponse;
import gr.accepted.matchoddsrestapi.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    AllMatchesResponse getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    Match getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id);
    }

    @PostMapping
    ResponseEntity<?> saveNewMatch(@RequestBody Match match) {
        matchService.saveNewMatch(match);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    ResponseEntity<?> saveNewMatch(@RequestBody Match match) {
//        matchService.saveNewMatch(match);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}