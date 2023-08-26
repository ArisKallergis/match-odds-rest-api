package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.model.dto.request.CreateMatchOddRequest;
import gr.accepted.matchoddsrestapi.model.dto.request.UpdateMatchOddRequest;
import gr.accepted.matchoddsrestapi.model.dto.response.AllMatchOddsResponse;
import gr.accepted.matchoddsrestapi.model.dto.response.MatchOddResponse;
import gr.accepted.matchoddsrestapi.service.MatchOddService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Match Odd", description = "crud operations for match odd entities")
@RestController
@RequestMapping(value = "match-odds")
public class MatchOddController {

    private final MatchOddService matchOddService;

    public MatchOddController(MatchOddService matchOddService) {
        this.matchOddService = matchOddService;
    }

    @GetMapping(produces = "application/json")
    ResponseEntity<AllMatchOddsResponse> getAllMatchOdds() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchOddService.getAllMatchOdds());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<MatchOddResponse> getMatchOddById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchOddService.getMatchOddById(id));
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity<?> saveNewMatchOdd(@RequestBody @Valid CreateMatchOddRequest matchOdd, HttpServletRequest request) {
        Long createdId = matchOddService.saveNewMatchOdd(matchOdd);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, request.getRequestURI() + "/" + createdId)
                .build();
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> updateMatchOdd(@RequestBody @Valid UpdateMatchOddRequest matchOddRequest, @PathVariable Long id) {
        matchOddService.updateMatchOdd(matchOddRequest, id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteMatchOdd(@PathVariable Long id) throws Exception {
        matchOddService.deleteMatchOdd(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
