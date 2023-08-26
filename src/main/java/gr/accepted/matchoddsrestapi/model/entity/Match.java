package gr.accepted.matchoddsrestapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.accepted.matchoddsrestapi.model.Sport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_generator")
    @SequenceGenerator(name = "match_generator", sequenceName = "match_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "match_time")
    private LocalTime matchTime;

    @Column(name = "team_a")
    private String teamA;

    @Column(name = "team_b")
    private String teamB;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "sport")
    private Sport sport;

    @OneToMany(mappedBy = "match", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<MatchOdd> matchOdds = new ArrayList<>();
}
