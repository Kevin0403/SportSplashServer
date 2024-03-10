package com.example.sportsplash.sports;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BadmintonMatchState {


    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private int team1Score;
    private int team2Score;

    private MatchStatus status;
    private int requiredScore;

    @ManyToOne
    private BadmintonMatch match;
}
