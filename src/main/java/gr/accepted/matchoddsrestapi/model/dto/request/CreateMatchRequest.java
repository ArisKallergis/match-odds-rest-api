package gr.accepted.matchoddsrestapi.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import gr.accepted.matchoddsrestapi.model.enums.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateMatchRequest {

    @Schema(example = "Some description")
    @Size(min = 1, max = 255)
    private String description;

    @Schema(example = "31/12/2023")
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate matchDate;

    @Schema(type="string", example = "13:42")
    @NotNull
    private LocalTime matchTime;

    @Schema(example = "OSFP")
    @NotNull
    @Size(min = 2, max = 10)
    private String teamA;

    @Schema(example = "PAO")
    @NotNull
    @Size(min = 2, max = 10)
    private String teamB;

    @Schema(examples = {"FOOTBALL", "football", "1"})
    @NotNull
    private Sport sport;

    @Valid
    private List<@Valid CreateNestedMatchOddRequest> matchOdds = new ArrayList<>();

    public Match toEntity() {
        Match match = new Match();
        List<MatchOdd> matchOdds = this.matchOdds.stream().map((matchOddRequest) -> matchOddRequest.toEntity(match)).toList();

        match.setId(null);
        match.setDescription(this.getDescription());
        match.setMatchDate(this.matchDate);
        match.setMatchTime(this.matchTime);
        match.setTeamA(this.teamA);
        match.setTeamB(this.teamB);
        match.setSport(this.sport);
        match.setMatchOdds(matchOdds);

        return match;
    }
}
