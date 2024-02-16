package com.example.sportsplash.sports;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tournament {

    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    String tournamentName;

    Game game;

    String startDate;

    String endDate;

    int teamSize;

    int teams;

    boolean isdefault=true;

    @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="email_FK",referencedColumnName = "email")
     private User user;

    public Tournament(int id, String tournamentName, Game game, String startDate, String endDate, int teamSize, int teams, boolean isdefault, User user) {
        this.id = id;
        this.tournamentName = tournamentName;
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamSize = teamSize;
        this.teams = teams;
        this.isdefault = isdefault;
        this.user = user;
    }


    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", tournamentName='" + tournamentName + '\'' +
                ", game=" + game +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", teamSize=" + teamSize +
                ", teams=" + teams +
                ", isdefault=" + isdefault +
                ", user=" + user +
                '}';
    }

    public User getUser() {

        if (user == null) {
            user = new User();
        }
        return user;

    }



}
