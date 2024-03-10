package com.example.sportsplash.sports;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KabaddiMatchState {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    KabaddiMatch match;

    int team1score = 0;
    int team2score = 0;

    int raidPoints1;
    int raidPoints2;
    int tacklePoints1;
    int tacklePoints2;
    int  bonusPoints1;
    int bonusPoints2;
    int alloutPoints1;
    int alloutPoints2;
    int technicalPoints1;
    int technicalPoints2;

    MatchStatus status;

}
