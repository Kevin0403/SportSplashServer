package com.example.sportsplash.sports;

import jakarta.persistence.*;

@Entity
public class Team {
    @Id
    int id;
    String name;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fk",referencedColumnName = "id")
    private Tournament tournament;

    public Tournament getTournament() {
        if (tournament == null) {
            tournament = new Tournament();
        }
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getTid() {
        return id;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tournament=" + tournament +
                '}';
    }

    public void setTid(int tid) {
        this.id = tid;
    }

    public Team() {
        super();
    }


    public Team(int id, String name, Tournament tournament) {
        this.id = id;
        this.name = name;
        this.tournament = tournament;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}