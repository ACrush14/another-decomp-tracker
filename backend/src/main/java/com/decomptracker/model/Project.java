package com.decomptracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column (name = "repo_url", nullable = false, length = 255)
    private String repoUrl;

    protected Project() {

    }

    public Project(String name, String repoUrl) {
        this.name = name;
        this.repoUrl = repoUrl;
    }

    public Long getId() {
    return id;
}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl (String repoUrl) {
        this.repoUrl = repoUrl;
    }

}

