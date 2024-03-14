package com.example.sportsplash.sports;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Future;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String tournamentName;

    Game game;

    @Future

    private Date startDate;

    @Future

    private Date endDate;

    int teamSize;

    int teams;

    boolean isdefault = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email_FK", referencedColumnName = "email")
    private User user;

    // Getters and setters
}
