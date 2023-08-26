package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.model.dto.request.CreateMatchRequest;
import gr.accepted.matchoddsrestapi.model.dto.request.UpdateMatchRequest;
import gr.accepted.matchoddsrestapi.model.dto.response.AllMatchesResponse;
import gr.accepted.matchoddsrestapi.service.MatchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Match", description = "crud operations for match entities")
@RestController
@RequestMapping(value = "matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(produces = "application/json")
    ResponseEntity<AllMatchesResponse<?>> getAllMatches(@RequestParam(defaultValue = "false") Boolean withDetails) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchService.getAllMatches(withDetails));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<?> getMatchById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchService.getMatchById(id));
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity<?> createNewMatch(@RequestBody @Valid CreateMatchRequest matchRequest, HttpServletRequest request) {
        Long createdId = matchService.saveNewMatch(matchRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, request.getRequestURI() + "/" + createdId)
                .build();
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> updateMatch(@RequestBody @Valid UpdateMatchRequest matchRequest, @PathVariable Long id) {
        matchService.updateMatch(matchRequest, id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteMatch(@PathVariable Long id) throws Exception {
        matchService.deleteMatch(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}