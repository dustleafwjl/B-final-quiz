package com.thoughtworks.capability.gtb.finalquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trainee {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String office;
    private String email;
    private String github;
    private String zoomId;

    @ManyToOne
    private Team team;
}
