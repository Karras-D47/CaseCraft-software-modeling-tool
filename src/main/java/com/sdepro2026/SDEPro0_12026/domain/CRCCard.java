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
@Table(name="crc_cards")
public class CRCCard {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable=false)
    private String className;
    
    

    @Column(columnDefinition = "TEXT")
    private String responsibilities;

    @Column(columnDefinition = "TEXT")
    private String collabs;

    @ManyToOne
    @JoinColumn(name = "project_id",nullable=false)
    private Project project;
    
   
    
    @ManyToMany
    @JoinTable(name = "crc_usecase_link",joinColumns = @JoinColumn(name = "crc_card_id"), inverseJoinColumns = @JoinColumn(name = "use_case_id"))
    private List<UseCase> linkedUseCases = new ArrayList<>();

    public Long getId(){
        return id;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getResponsibilities(){
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities){
        this.responsibilities = responsibilities;
    }

    public String getCollaborations(){
        return collabs;
    }

    public void setCollaborations(String collabs){
        this.collabs = collabs;
    }

    public Project getProject(){
        return project;
    }

    public void setProject(Project project){
        this.project = project;
    }

    public List<UseCase> getLinkedUseCases(){
        return linkedUseCases;
    }

    public void setLinkedUseCases(List<UseCase> linkedUseCases){
        this.linkedUseCases = linkedUseCases;
    }
    
}
