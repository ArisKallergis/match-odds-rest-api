package gr.accepted.matchoddsrestapi.model.dto.request;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UpdateNestedMatchOddRequest {

    @Schema(description = "if null, creates new match odd, else tries to update existing one")
    private Long id;

    @Schema(example = "X")
    @NotNull
    @Size(min = 1, max = 1)
    private String specifier;

    @Schema(example = "1.3")
    @NotNull
    private BigDecimal odd;

    @Schema(example = "67")
    private Long matchId;

    public MatchOdd toEntity(@Valid Match match) {
        return new MatchOdd(
                this.id,
                this.specifier,
                this.odd,
                match
        );
    }
}
