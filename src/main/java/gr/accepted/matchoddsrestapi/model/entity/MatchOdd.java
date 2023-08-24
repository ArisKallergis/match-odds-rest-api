package gr.accepted.matchoddsrestapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class MatchOdd {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odd_generator")
    @SequenceGenerator(name = "match_odd_generator", sequenceName = "match_odd_seq", allocationSize = 1)
    private Long id;
    private Character specifier;
    private BigDecimal odd;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

}
