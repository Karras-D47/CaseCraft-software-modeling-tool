/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sdepro2026.SDEPro0_12026.domain;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/**
 *
 * @author k3rneluser
 */

@Entity
@Table(name="projects")
public class Project {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    @Column(nullable=false)
    private String proTitle;

    private String proDescription;
    
    @ManyToOne
    @JoinColumn(name = "owner_id",nullable=false)
    private User owner;
    
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UseCase> useCases  = new ArrayList<>();
    
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CRCCard> crcCardList = new ArrayList<>();
    
    @OneToMany(mappedBy="project",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Actor> actorsList = new ArrayList<>();

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return proTitle;
    }

    public void setName(String proTitle){
        this.proTitle=proTitle;
    }

    public String getDescription(){
        return proDescription;
    }

    public void setDescription(String proDescription){
        this.proDescription = proDescription;
    }


    public User getOwner(){
        return owner;
    }

    public void setOwner(User owner){
        this.owner = owner;
    }

    public List<UseCase> getUseCases(){
        return useCases;
    }

    public void setUseCases(List<UseCase> useCases){
        this.useCases = useCases;
    }

    public List<CRCCard> getCRCCards(){
        return crcCardList;
    }
    
    public void setCRCCards(List<CRCCard> crcCardList){
        this.crcCardList = crcCardList;
    }

    public List<Actor> getActors(){
        return actorsList;
    }

    public void setActors(List<Actor> actorsList){
        this.actorsList = actorsList;
    }

    
}
