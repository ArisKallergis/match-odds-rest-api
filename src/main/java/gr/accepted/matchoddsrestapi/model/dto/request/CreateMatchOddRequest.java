package gr.accepted.matchoddsrestapi.model.dto.request;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateMatchOddRequest {

    @Schema(example = "X")
    @NotNull
    @Size(min = 1, max = 1)
    private String specifier;

    @Schema(example = "2.3")
    @NotNull
    @Positive
    private BigDecimal odd;

    @Schema(example = "23")
    @NotNull
    private Long matchId;

    public MatchOdd toEntity() {
        Match match = new Match();
        match.setId(this.matchId);
        return new MatchOdd(
                null,
                this.specifier,
                this.odd,
                match
        );
    }
}
