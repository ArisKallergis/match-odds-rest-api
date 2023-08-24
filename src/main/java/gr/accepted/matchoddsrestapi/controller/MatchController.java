package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.response.AllMatchesResponse;
import gr.accepted.matchoddsrestapi.service.MatchService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    ResponseEntity<AllMatchesResponse> getAllMatches() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AllMatchesResponse(matchService.getAllMatches()));
    }

    @GetMapping("/{id}")
    ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchService.getMatchById(id));
    }

    @PostMapping
    ResponseEntity<?> saveNewMatch(@RequestBody Match match, HttpServletRequest request) {
        Long createdId = matchService.saveNewMatch(match);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, request.getRequestURI() + "/" + createdId)
                .build();
    }
}