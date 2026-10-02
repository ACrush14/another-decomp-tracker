package com.decomptracker.etl;

import  java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties (ignoreUnknown = true) 
public record GitHubCommit (String sha, Detail commit)  {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Detail (Person committer) {

    }
    
    @JsonIgnoreProperties (ignoreUnknown = true) 
    public record Person(Instant date) {

    }
    public Instant committedAt()  {
        return commit().committer().date();
    }

}
