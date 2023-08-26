package gr.accepted.matchoddsrestapi.model.dto.request;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateNestedMatchOddRequest {

    @NotNull
    @Size(min = 1, max = 1)
    private String specifier;

    @NotNull
    @Positive
    private BigDecimal odd;

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
