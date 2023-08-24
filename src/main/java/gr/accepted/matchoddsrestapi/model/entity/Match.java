package gr.accepted.matchoddsrestapi.model.entity;

import gr.accepted.matchoddsrestapi.model.Sport;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_generator")
    @SequenceGenerator(name = "match_generator", sequenceName = "match_seq", allocationSize = 1)
    private Long id;
    private String description;
    private LocalDate match_date;
    private LocalTime match_time;
    @Column(name = "team_a")
    private String teamA;
    @Column(name = "team_b")
    private String teamB;
    private Sport sport;

    @OneToMany(mappedBy = "match")
    private List<MatchOdd> matchOdds;
}
