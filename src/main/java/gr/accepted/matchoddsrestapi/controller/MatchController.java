package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.model.dto.request.CreateMatchRequest;
import gr.accepted.matchoddsrestapi.model.dto.request.UpdateMatchRequest;
import gr.accepted.matchoddsrestapi.model.dto.response.AllMatchesResponse;
import gr.accepted.matchoddsrestapi.model.dto.response.MatchWithDetailsResponse;
import gr.accepted.matchoddsrestapi.model.error.ApiError;
import gr.accepted.matchoddsrestapi.service.MatchService;
import gr.accepted.util.SwaggerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Match", description = "CRUD operations for match entities")
@RestController
@RequestMapping(value = "matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @Operation(summary = "Get all matches", description = "Get all matches, with option to see details about match odds")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Successful operation", content = @Content(mediaType = "application/json", examples = {@ExampleObject(name = "All matches", value = SwaggerUtils.ALL_MATCHES_RESPONSE), @ExampleObject(name = "All matches with details", value = SwaggerUtils.ALL_MATCHES_WITH_DETAILS_RESPONSE)}))
    })
    @GetMapping(produces = "application/json")
    ResponseEntity<AllMatchesResponse<?>> getAllMatches(@RequestParam(defaultValue = "false") Boolean withDetails) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchService.getAllMatches(withDetails));
    }

    @Operation(summary = "Get match by id", description = "Get the match with the specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Successful operation", content = @Content(schema = @Schema(implementation = MatchWithDetailsResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404 Not Found", description = "Match not Found", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Match not found", value = SwaggerUtils.MATCH_NOT_FOUND_RESPONSE)}))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<MatchWithDetailsResponse> getMatchById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchService.getMatchById(id));
    }

    @Operation(summary = "Create match", description = "Create a new match, possibly creating related match odds, too")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201 Created", description = "Match created successfully", headers = @Header(name = "Location", schema = @Schema(example = "/matches/4"))),
            @ApiResponse(responseCode = "400 Bad Request", description = "Creation didn't happen due to invalid input", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Team names too big", value = SwaggerUtils.CREATE_MATCH_INVALID_INPUT_RESPONSE)}))
    })
    @PostMapping(consumes = "application/json")
    ResponseEntity<?> createNewMatch(@RequestBody @Valid CreateMatchRequest matchRequest, HttpServletRequest request) {
        Long createdId = matchService.saveNewMatch(matchRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, request.getRequestURI() + "/" + createdId)
                .build();
    }

    @Operation(summary = "Update match", description = "Update an existing match, creating or updating possible given match odds")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204 No Content", description = "Match updated successfully"),
            @ApiResponse(responseCode = "400 Bad Request", description = "Update didn't happen due to invalid input", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Id mismatch", value = SwaggerUtils.ID_MISMATCH_RESPONSE), @ExampleObject(name = "Validation error in match odds", value = SwaggerUtils.MATCH_ODD_INVALID_RESPONSE)})),
            @ApiResponse(responseCode = "404 Not Found", description = "Match not Found", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Match not found", value = SwaggerUtils.MATCH_NOT_FOUND_RESPONSE)}))
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> updateMatch(@RequestBody @Valid UpdateMatchRequest matchRequest, @PathVariable Long id) {
        matchService.updateMatch(matchRequest, id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Delete match", description = "Delete an existing match, also deleting match odds related to it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204 No Content", description = "Match and match odd children deleted successfully"),
            @ApiResponse(responseCode = "404 Not Found", description = "Match not Found", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Match not found", value = SwaggerUtils.MATCH_NOT_FOUND_RESPONSE)}))
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteMatch(@PathVariable Long id) throws Exception {
        matchService.deleteMatch(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}