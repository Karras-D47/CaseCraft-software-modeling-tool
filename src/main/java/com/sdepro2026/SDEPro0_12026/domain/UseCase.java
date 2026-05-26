/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sdepro2026.SDEPro0_12026.domain;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author k3rneluser
 */

@Entity
@Table(name="use_cases")
public class UseCase {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    @Column(nullable=false)
    private String ucName;

    @Column(columnDefinition="TEXT")
    private String ucPreCond;
    
    @Column(columnDefinition = "TEXT")
    private String kyrioFlow;
    
    @Column(columnDefinition = "TEXT")
    private String deutervonFlow;
    @Column(columnDefinition = "TEXT")
    private String ucPostCond;
    
    @ManyToOne 
    @JoinColumn(name = "project_id",nullable=false)
    private Project project;
    
    @ManyToMany
    @JoinTable(name = "usecase_actors",joinColumns = @JoinColumn(name = "usecase_id"),inverseJoinColumns = @JoinColumn(name = "actor_id"))
    
    private List<Actor> actors = new ArrayList<>();
    
    public Long getId(){
        return id;
    }

    public String getName(){
        return ucName;
    }

    public void setName(String ucName){
        this.ucName = ucName;
    }

    public String getPreconditions(){
        return ucPreCond;
    }

    public void setPreconditions(String ucPreCond){
        this.ucPreCond = ucPreCond;
    }

    public String getMainFlow(){
        return kyrioFlow;
    }

    public void setMainFlow(String kyrioFlow){
        this.kyrioFlow = kyrioFlow;
    }

    public String getAlternativeFlow(){
        return deutervonFlow;
    }

    public void setAlternativeFlow(String deutervonFlow){
        this.deutervonFlow=deutervonFlow;
    }

    public String getPostconditions(){
        return ucPostCond;
    }

    public void setPostconditions(String ucPostCond){
        this.ucPostCond = ucPostCond;
    }

    public Project getProject(){
        return project;
    }

    public void setProject(Project project){
        this.project = project;
    }

    public List<Actor> getActors(){
        return actors;
    }

    public void setActors(List<Actor> actorsList){
        this.actors=actorsList;
    }

    
}
