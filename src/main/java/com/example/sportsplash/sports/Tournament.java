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
@AllArgsConstructor
public class Tournament {

    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    String tournamentName;

    Game game;

    String startDate;

    String endDate;

    int teamSize;

    int teams;

    @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="email_FK",referencedColumnName = "email")
     private User user;








    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", tournamentName='" + tournamentName + '\'' +
                ", game='" + game + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", teamSize=" + teamSize +
                ", teams=" + teams +
                ", user=" + user +
                '}';
    }

    public User getUser() {

        if (user == null) {
            user = new User();  // Create a new User object if it's null
        }
        return user;

    }



}
