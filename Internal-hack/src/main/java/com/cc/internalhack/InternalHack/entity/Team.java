package com.cc.internalhack.InternalHack.entity;

import com.cc.internalhack.InternalHack.entity.enums.Theme;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Team_Name should not be empty")
    private String teamName;

    @Size(min = 10, max = 250, message = "Descr should between 10-250 char")
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_leader_id")
    private User teamLeader;

    @OneToMany
    @JoinTable(
            name = "team_members",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> members = new HashSet<>();

    @ElementCollection(targetClass = Theme.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Theme> theme;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProjectSubmission> projectSubmissions;
}
