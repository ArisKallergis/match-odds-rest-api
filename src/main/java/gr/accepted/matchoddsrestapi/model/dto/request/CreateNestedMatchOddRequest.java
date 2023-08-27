package gr.accepted.matchoddsrestapi.model.dto.request;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "if id is sent, it is ignored")
@Data
@NoArgsConstructor
public class CreateNestedMatchOddRequest {

    @Schema(example = "X")
    @NotNull
    @Size(min = 1, max = 1)
    private String specifier;

    @Schema(example = "3.4")
    @NotNull
    @Positive
    private BigDecimal odd;

    @Schema(example = "56")
    private Long matchId;

    public MatchOdd toEntity(@Valid Match match) {
        return new MatchOdd(
                null,
                this.specifier,
                this.odd,
                match
        );
    }
}
