package com.thoughtworks.capability.gtb.finalquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Trainee> trainees;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Trainer> trainers;
}
