package gr.accepted.matchoddsrestapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match_odd")
public class MatchOdd {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odd_generator")
    @SequenceGenerator(name = "match_odd_generator", sequenceName = "match_odd_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "specifier")
    private Character specifier;

    @Column(name = "odd")
    private BigDecimal odd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "match_id")
    private Match match;
}
