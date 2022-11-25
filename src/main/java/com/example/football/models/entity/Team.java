package com.example.football.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, name = "stadium_name")
    private String stadiumName;

    @Column(nullable = false, name = "fan_base")
    private int fanBase;

    @Column(nullable = false, columnDefinition = "text")
    private String history;

    @ManyToOne(optional = false)
    private Town town;

//    @OneToMany(targetEntity =  Player.class, mappedBy = "team")
//    private Set<Player> players;

}
