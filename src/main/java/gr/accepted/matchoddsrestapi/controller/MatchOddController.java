package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import gr.accepted.matchoddsrestapi.model.response.AllMatchOddsResponse;
import gr.accepted.matchoddsrestapi.service.MatchOddService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping(value = "match-odds")
public class MatchOddController {

    private final MatchOddService matchOddService;

    public MatchOddController(MatchOddService matchOddService) {
        this.matchOddService = matchOddService;
    }

    @GetMapping
    ResponseEntity<AllMatchOddsResponse> getAllMatchOdds() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AllMatchOddsResponse(matchOddService.getAllMatchOdds()));
    }
}
