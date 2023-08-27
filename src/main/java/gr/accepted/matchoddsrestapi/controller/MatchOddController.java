package gr.accepted.matchoddsrestapi.controller;

import gr.accepted.matchoddsrestapi.model.dto.request.CreateMatchOddRequest;
import gr.accepted.matchoddsrestapi.model.dto.request.UpdateMatchOddRequest;
import gr.accepted.matchoddsrestapi.model.dto.response.AllMatchOddsResponse;
import gr.accepted.matchoddsrestapi.model.dto.response.MatchOddResponse;
import gr.accepted.matchoddsrestapi.model.error.ApiError;
import gr.accepted.matchoddsrestapi.service.MatchOddService;
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

@Tag(name = "Match Odd", description = "CRUD operations for match odd entities")
@RestController
@RequestMapping(value = "match-odds")
public class MatchOddController {

    private final MatchOddService matchOddService;

    public MatchOddController(MatchOddService matchOddService) {
        this.matchOddService = matchOddService;
    }

    @Operation(summary = "Get all match odds", description = "Get all match odds")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Successful operation", content = @Content(schema = @Schema(implementation = AllMatchOddsResponse.class), mediaType = "application/json"))
    })
    @GetMapping(produces = "application/json")
    ResponseEntity<AllMatchOddsResponse> getAllMatchOdds() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchOddService.getAllMatchOdds());
    }

    @Operation(summary = "Get match odd by id", description = "Get the match odd with the specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Successful operation", content = @Content(schema = @Schema(implementation = MatchOddResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404 Not Found", description = "Match odd not Found", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Match odd not found", value = SwaggerUtils.MATCH_ODD_NOT_FOUND_RESPONSE)}))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<MatchOddResponse> getMatchOddById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(matchOddService.getMatchOddById(id));
    }

    @Operation(summary = "Create match odd", description = "Create a new match odd, for the specified (by id) match")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201 Created", description = "Match odd created successfully", headers = @Header(name = "Location", schema = @Schema(example = "/match-odds/13"))),
            @ApiResponse(responseCode = "400 Bad Request", description = "Creation didn't happen due to invalid input", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Match id null", value = SwaggerUtils.CREATE_MATCH_ODD_MISSING_MATCH_ID_RESPONSE)}))
    })
    @PostMapping(consumes = "application/json")
    ResponseEntity<?> saveNewMatchOdd(@RequestBody @Valid CreateMatchOddRequest matchOdd, HttpServletRequest request) {
        Long createdId = matchOddService.saveNewMatchOdd(matchOdd);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, request.getRequestURI() + "/" + createdId)
                .build();
    }

    @Operation(summary = "Update match odd", description = "Update an existing match odd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204 No Content", description = "Match odd updated successfully"),
            @ApiResponse(responseCode = "400 Bad Request", description = "Update didn't happen due to invalid input", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Match id null", value = SwaggerUtils.UPDATE_MATCH_ODD_MISSING_MATCH_ID_RESPONSE)})),
            @ApiResponse(responseCode = "404 Not Found", description = "Match odd not Found", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Match odd not found", value = SwaggerUtils.MATCH_ODD_NOT_FOUND_RESPONSE)}))
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> updateMatchOdd(@RequestBody @Valid UpdateMatchOddRequest matchOddRequest, @PathVariable Long id) {
        matchOddService.updateMatchOdd(matchOddRequest, id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Delete match odd", description = "Delete an existing match odd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204 No Content", description = "Match odd deleted successfully"),
            @ApiResponse(responseCode = "404 Not Found", description = "Match odd not Found", content = @Content(schema = @Schema(implementation = ApiError.class), mediaType = "application/json", examples = {@ExampleObject(name = "Match odd not found", value = SwaggerUtils.MATCH_ODD_NOT_FOUND_RESPONSE)}))
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteMatchOdd(@PathVariable Long id) throws Exception {
        matchOddService.deleteMatchOdd(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
