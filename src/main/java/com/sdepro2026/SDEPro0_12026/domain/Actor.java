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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/**
 *
 * @author k3rneluser
 */

@Entity
@Table(name="actors")
public class Actor {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable=false)
    private String actorName;

    @ManyToOne
    @JoinColumn(name="project_id",nullable=false)
    private Project project;
    
    @ManyToMany(mappedBy = "actors")
    private List<UseCase> useCases = new ArrayList<>();

    public Long getId(){
        return id;
    }

    public String getName(){
        return actorName;
    }

    public void setName(String actorName){
        this.actorName= actorName;
    }

    public Project getProject(){
        return project;
    }

    public void setProject(Project project ){
        this.project = project;
    }

    public List<UseCase> getUseCases(){
        return useCases;
    }

    public void setUseCases(List<UseCase> useCases){
        this.useCases = useCases;
    }
    

}
