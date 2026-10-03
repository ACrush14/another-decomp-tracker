package com.decomptracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity 
@Table(name = "game")
public class Game {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "title", nullable = false, unique = true, length = 150)
    private String title;

    @Column(name = "repo_url", length = 255)
    private String repoUrl;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "progress_percent", precision = 5, scale = 2)
    private BigDecimal progressPercent;

    @Column(name = "progress_metric", nullable = false, length = 40)
    private String progressMetric;

    @Column (name = "matched_functions")
    private Integer matchedFunctions;

    @Column (name = "total_functions")
    private Integer totalFunctions;

    @Column (name = "progress_note", length = 1000)
    private String progressNote;

    @Column(name = "source_url", length =255)
    private String sourceUrl;

    @Column ( name = "checked_on")
    private LocalDate checkedOn;

    protected Game() {

    }

    public Game(String title, String repoUrl, String status, BigDecimal progressPercent,
                String progressMetric, Integer matchedFunctions, Integer totalFunctions,
                String progressNote, String sourceUrl, LocalDate checkedOn) {
                    this.title = title;
                    this.repoUrl = repoUrl;
                    this.status = status;
                    this.progressPercent = progressPercent;
                    this.progressMetric = progressMetric;
                    this.matchedFunctions = matchedFunctions;
                    this.totalFunctions = totalFunctions;
                    this.progressNote = progressNote;
                    this.sourceUrl = sourceUrl;
                    this.checkedOn = checkedOn;
                }
    
    
    public Long getId() { return id;}
    public String getTitle() { return title;}
    public String getRepoUrl() { return repoUrl;}
    public String getStatus() { return status; }
    public BigDecimal getProgressPercent() { return progressPercent; }
    public String getProgressMetric() { return progressMetric; }
    public Integer getMatchedFunctions() { return matchedFunctions; }
    public Integer getTotalFunctions() { return totalFunctions; }
    public String getProgressNote() { return progressNote; }
    public String getSourceUrl() { return sourceUrl; }
    public LocalDate getCheckedOn() { return checkedOn; }
    

    public void update(String repoUrl, String status, BigDecimal progressPercent,
                        String progressMetric, Integer matchedFunction, Integer totalFunctions,
                        String progressNote, String sourceUrl, LocalDate checkedOn) {
                            this.repoUrl = repoUrl;
                                    this.status = status;
        this.progressPercent = progressPercent;
        this.progressMetric = progressMetric;
        this.matchedFunctions = matchedFunctions;
        this.totalFunctions = totalFunctions;
        this.progressNote = progressNote;
        this.sourceUrl = sourceUrl;
        this.checkedOn = checkedOn;
    }
}