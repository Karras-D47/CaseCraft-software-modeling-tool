package com.sdepro2026.SDEPro0_12026.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdepro2026.SDEPro0_12026.domain.Actor;
import com.sdepro2026.SDEPro0_12026.domain.CRCCard;
import com.sdepro2026.SDEPro0_12026.domain.Project;
import com.sdepro2026.SDEPro0_12026.domain.UseCase;
import com.sdepro2026.SDEPro0_12026.domain.User;
import com.sdepro2026.SDEPro0_12026.generation.classdiagram.ClassDiagramGeneratorFactory;
import com.sdepro2026.SDEPro0_12026.generation.classdiagram.ClassDiagramStrategy;
import com.sdepro2026.SDEPro0_12026.generation.usecase.UseCaseDiagramGeneratorFactory;
import com.sdepro2026.SDEPro0_12026.generation.usecase.UseCaseDiagramStrategy;
import com.sdepro2026.SDEPro0_12026.repositories.ActorRepository;
import com.sdepro2026.SDEPro0_12026.repositories.CRCCardRepository;
import com.sdepro2026.SDEPro0_12026.repositories.ProjectRepository;
import com.sdepro2026.SDEPro0_12026.repositories.UseCaseRepository;


@Service
public class ProjectsService{
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UseCaseRepository useCaseRepository;
    @Autowired
    private CRCCardRepository crcCardRepository;
    @Autowired
    private ActorRepository actorRepository;

    public List<Project> getProjectsForUser(User user){
        return projectRepository.findByOwner(user);

    }

    @Transactional
    public Project createProject(String name,String description,User owner){
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setOwner(owner);
        return projectRepository.save(project);
    }

    @Transactional
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }   

    @Transactional
    public void  deleteProject(Long projectId){
        projectRepository.deleteById(projectId);
    }

    public Project findProjectByID(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found :" + id));

    }

    @Transactional
    public UseCase saveUseCase(UseCase useCase){
        return useCaseRepository.save(useCase);
    }

    public List<UseCase> getUseCasesForProject(Project project){
        return useCaseRepository.findByProject(project);
    }


    @Transactional
    public void deleteUseCase(Long useCaseID){
        useCaseRepository.deleteById(useCaseID);
    }

    @Transactional
    public CRCCard saveCRCCard(CRCCard card){
        return crcCardRepository.save(card);
    }


    @Transactional
    public void linkCRCCardToUseCase(Long cardID , Long useCaseID){
        CRCCard card = crcCardRepository.findById(cardID).orElseThrow(() -> new RuntimeException("CRCCard not found :" + cardID));
        UseCase useCase = useCaseRepository.findById(useCaseID).orElseThrow(() -> new RuntimeException("UseCase not found : " + useCaseID));

        card.getLinkedUseCases().add(useCase);
        crcCardRepository.save(card);
    }

    @Transactional
    public void deleteCRCCard(Long cardId){
        crcCardRepository.deleteById(cardId);
    }

    @Transactional
    public Actor saveActor(Actor actor){
        return actorRepository.save(actor);
    }

    @Transactional
    public void deleteActor(Long actorId){
        actorRepository.deleteById(actorId);
    }
    
    public String generateUseCaseDiagram(Long projectId, String tool) {
        Project project = findProjectByID(projectId);
        UseCaseDiagramStrategy strategy = UseCaseDiagramGeneratorFactory.create(tool);
        return strategy.generateScript(project);
    }

    public String generateClassDiagram(Long projectId,String tool){
        Project project = findProjectByID(projectId);
        ClassDiagramStrategy strategy = ClassDiagramGeneratorFactory.create(tool);
        return strategy.generateScript(project);
    }

    public UseCase findUseCaseById(Long id) {
        return useCaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UseCase not found: " + id));
    }

    public CRCCard findCRCCardById(Long id) {
        return crcCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CRCCard not found: " + id));
    }

    public List<Actor> findActorsByIds(List<Long> actorIds) {
        if (actorIds == null || actorIds.isEmpty()) {
            return new ArrayList<>();
        }
        return actorRepository.findAllById(actorIds);
    }
}