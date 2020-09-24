package com.thoughtworks.capability.gtb.finalquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @JsonIgnore
    @JoinColumn(name = "team_id")
    private Team team;

    @Transient
    private String teamName;

    @PostLoad
    public void setTeamName() {
        if(team == null) {
            this.teamName = "";
            return ;
        }
        this.teamName = team.getName();
    }
}
