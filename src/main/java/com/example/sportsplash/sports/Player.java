package com.example.sportsplash.sports;

import jakarta.persistence.*;

@Entity
public class Player {
    public Player(int id, String name, Team team) {
        this.id = id;
        this.name = name;
        this.team = team;
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

    public Team getTeam() {
        if (team == null) {
            team = new Team();  // Create a new User object if it's null
        }
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Id@GeneratedValue
    int id;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=" + team +
                '}';
    }

    String name;
    @ManyToOne
    @JoinColumn(name="id_fk",referencedColumnName = "id")
    private Team team;


    public Player() {
        super();
    }

    public Player(int id) {
        this.id = id;
    }
}
