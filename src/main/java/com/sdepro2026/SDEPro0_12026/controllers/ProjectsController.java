package com.sdepro2026.SDEPro0_12026.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sdepro2026.SDEPro0_12026.domain.Actor;
import com.sdepro2026.SDEPro0_12026.domain.CRCCard;
import com.sdepro2026.SDEPro0_12026.domain.Project;
import com.sdepro2026.SDEPro0_12026.domain.UseCase;
import com.sdepro2026.SDEPro0_12026.domain.User;
import com.sdepro2026.SDEPro0_12026.services.ProjectsService;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;

    @GetMapping
    public String listProjects(@AuthenticationPrincipal User currentUser, Model model) {
        List<Project> projects = projectsService.getProjectsForUser(currentUser);

        int totalProjects = projects.size();

        int totalUseCases = projects.stream()
                .mapToInt(project -> project.getUseCases() != null ? project.getUseCases().size() : 0)
                .sum();

        int totalCRCCards = projects.stream()
                .mapToInt(project -> project.getCRCCards() != null ? project.getCRCCards().size() : 0)
                .sum();

        int totalActors = projects.stream()
                .mapToInt(project -> project.getActors() != null ? project.getActors().size() : 0)
                .sum();

        model.addAttribute("projects", projects);
        model.addAttribute("totalProjects", totalProjects);
        model.addAttribute("totalUseCases", totalUseCases);
        model.addAttribute("totalCRCCards", totalCRCCards);
        model.addAttribute("totalActors", totalActors);

        return "projects/list";
    }
    @PostMapping("/create")
    public String createProject(@AuthenticationPrincipal User currentUser,
                                @RequestParam String name,
                                @RequestParam String description,
                                RedirectAttributes redirectAttributes) {
        projectsService.createProject(name, description, currentUser);
        redirectAttributes.addFlashAttribute("success", "Project created successfully.");
        return "redirect:/projects";
    }

    @PostMapping("/{projectId}/delete")
    public String deleteProject(@PathVariable Long projectId,
                                RedirectAttributes redirectAttributes) {
        projectsService.deleteProject(projectId);
        redirectAttributes.addFlashAttribute("success", "Project deleted.");
        return "redirect:/projects";
    }

    @GetMapping("/{projectId}/edit")
    public String showEditProject(@PathVariable Long projectId, Model model) {
        Project project = projectsService.findProjectByID(projectId);

        model.addAttribute("project", project);

        return "projects/editProject";
    }

    @PostMapping("/{projectId}/edit")
    public String updateProject(@PathVariable Long projectId,
                                @RequestParam String name,
                                @RequestParam(required = false) String description,
                                RedirectAttributes redirectAttributes) {

        Project project = projectsService.findProjectByID(projectId);

        project.setName(name);
        project.setDescription(description);

        projectsService.saveProject(project);

        redirectAttributes.addFlashAttribute("success", "Project updated successfully.");

        return "redirect:/projects/" + projectId;
    }

    @GetMapping("/{projectId}")
    public String showProject(@PathVariable Long projectId,
                            @RequestParam(required = false) String tab,
                            Model model) {
        Project project = projectsService.findProjectByID(projectId);
        model.addAttribute("project", project);
        model.addAttribute("useCases", projectsService.getUseCasesForProject(project));
        model.addAttribute("newUseCase", new UseCase());
        model.addAttribute("newCRCCard", new CRCCard());
        model.addAttribute("newActor", new Actor());
        model.addAttribute("actors", project.getActors());
        model.addAttribute("activeTab", tab);
        return "projects/detail";
    }

    @PostMapping("/{projectId}/usecases/create")
    public String createUseCase(@PathVariable Long projectId,
                                @RequestParam String name,
                                @RequestParam String preconditions,
                                @RequestParam String mainFlow,
                                @RequestParam String alternativeFlow,
                                @RequestParam String postconditions,
                                @RequestParam(required = false) List<Long> actorIds,
                                RedirectAttributes redirectAttributes) {
        Project project = projectsService.findProjectByID(projectId);
        UseCase useCase = new UseCase();
        useCase.setName(name);
        useCase.setPreconditions(preconditions);
        useCase.setMainFlow(mainFlow);
        useCase.setAlternativeFlow(alternativeFlow);
        useCase.setPostconditions(postconditions);
        useCase.setProject(project);
        useCase.setActors(projectsService.findActorsByIds(actorIds));
        projectsService.saveUseCase(useCase);
        redirectAttributes.addFlashAttribute("success", "Use case created.");
        return "redirect:/projects/" + projectId + "?tab=usecases";
    }

    @GetMapping("/{projectId}/usecases/{useCaseId}/edit")
    public String showEditUseCase(@PathVariable Long projectId,
                                 @PathVariable Long useCaseId,
                                 Model model) {
        Project project = projectsService.findProjectByID(projectId);
        UseCase useCase = projectsService.findUseCaseById(useCaseId);
        model.addAttribute("project", project);
        model.addAttribute("useCase", useCase);
        model.addAttribute("actors", project.getActors());
        return "projects/editUseCase";
    }

    @PostMapping("/{projectId}/usecases/{useCaseId}/edit")
    public String updateUseCase(@PathVariable Long projectId,
                                @PathVariable Long useCaseId,
                                @RequestParam String name,
                                @RequestParam String preconditions,
                                @RequestParam String mainFlow,
                                @RequestParam String alternativeFlow,
                                @RequestParam String postconditions,
                                @RequestParam(required = false) List<Long> actorIds,
                                RedirectAttributes redirectAttributes) {
        UseCase useCase = projectsService.findUseCaseById(useCaseId);
        useCase.setName(name);
        useCase.setPreconditions(preconditions);
        useCase.setMainFlow(mainFlow);
        useCase.setAlternativeFlow(alternativeFlow);
        useCase.setPostconditions(postconditions);
        useCase.setActors(projectsService.findActorsByIds(actorIds)); 
        projectsService.saveUseCase(useCase);
        redirectAttributes.addFlashAttribute("success", "Use case updated.");
        return "redirect:/projects/" + projectId + "?tab=usecases";
    }

    @PostMapping("/{projectId}/usecases/{useCaseId}/delete")
    public String deleteUseCase(@PathVariable Long projectId,
                                @PathVariable Long useCaseId,
                                RedirectAttributes redirectAttributes) {
        projectsService.deleteUseCase(useCaseId);
        redirectAttributes.addFlashAttribute("success", "Use case deleted.");
        return "redirect:/projects/" + projectId + "?tab=usecases";
    }

    @PostMapping("/{projectId}/crccards/create")
    public String createCRCCard(@PathVariable Long projectId,
                                @RequestParam String className,
                                @RequestParam String responsibilities,
                                @RequestParam String collaborations,
                                RedirectAttributes redirectAttributes) {
        Project project = projectsService.findProjectByID(projectId);
        CRCCard card = new CRCCard();
        card.setClassName(className);
        card.setResponsibilities(responsibilities);
        card.setCollaborations(collaborations);
        card.setProject(project);
        projectsService.saveCRCCard(card);
        redirectAttributes.addFlashAttribute("success", "CRC card created.");
        return "redirect:/projects/" + projectId + "?tab=crc";
    }

    @GetMapping("/{projectId}/crccards/{cardId}/edit")
    public String showEditCRCCard(@PathVariable Long projectId,
                                  @PathVariable Long cardId,
                                  Model model) {
        Project project = projectsService.findProjectByID(projectId);
        CRCCard card = projectsService.findCRCCardById(cardId);
        List<UseCase> useCases = projectsService.getUseCasesForProject(project);
        model.addAttribute("project", project);
        model.addAttribute("card", card);
        model.addAttribute("useCases", useCases);
        return "projects/editCRCCard";
    }

    @PostMapping("/{projectId}/crccards/{cardId}/edit")
    public String updateCRCCard(@PathVariable Long projectId,
                                @PathVariable Long cardId,
                                @RequestParam String className,
                                @RequestParam String responsibilities,
                                @RequestParam String collaborations,
                                RedirectAttributes redirectAttributes) {
        CRCCard card = projectsService.findCRCCardById(cardId);
        card.setClassName(className);
        card.setResponsibilities(responsibilities);
        card.setCollaborations(collaborations);
        projectsService.saveCRCCard(card);
        redirectAttributes.addFlashAttribute("success", "CRC card updated.");
        return "redirect:/projects/" + projectId + "?tab=crc";
    }

    @PostMapping("/{projectId}/crccards/{cardId}/link")
    public String linkCRCCardToUseCase(@PathVariable Long projectId,
                                       @PathVariable Long cardId,
                                       @RequestParam Long useCaseId,
                                       RedirectAttributes redirectAttributes) {
        projectsService.linkCRCCardToUseCase(cardId, useCaseId);
        redirectAttributes.addFlashAttribute("success", "CRC card linked to use case.");
        return "redirect:/projects/" + projectId + "/crccards/" + cardId + "/edit";
    }

    @PostMapping("/{projectId}/crccards/{cardId}/delete")
    public String deleteCRCCard(@PathVariable Long projectId,
                                @PathVariable Long cardId,
                                RedirectAttributes redirectAttributes) {
        projectsService.deleteCRCCard(cardId);
        redirectAttributes.addFlashAttribute("success", "CRC card deleted.");
        return "redirect:/projects/" + projectId + "?tab=crc";
    }

    @PostMapping("/{projectId}/actors/create")
    public String createActor(@PathVariable Long projectId,
                              @RequestParam String name,
                              RedirectAttributes redirectAttributes) {
        Project project = projectsService.findProjectByID(projectId);
        Actor actor = new Actor();
        actor.setName(name);
        actor.setProject(project);
        projectsService.saveActor(actor);
        redirectAttributes.addFlashAttribute("success", "Actor created.");
        return "redirect:/projects/" + projectId + "?tab=actors";
    }

    @PostMapping("/{projectId}/actors/{actorId}/delete")
    public String deleteActor(@PathVariable Long projectId,
                              @PathVariable Long actorId,
                              RedirectAttributes redirectAttributes) {
        projectsService.deleteActor(actorId);
        redirectAttributes.addFlashAttribute("success", "Actor deleted.");
        return "redirect:/projects/" + projectId + "?tab=actors";
    }

    @GetMapping("/{projectId}/diagram/usecase")
    public String generateUseCaseDiagram(@PathVariable Long projectId,
                                         @RequestParam String tool,
                                         Model model) {
        String script = projectsService.generateUseCaseDiagram(projectId, tool);
        model.addAttribute("script", script);
        model.addAttribute("tool", tool);
        model.addAttribute("projectId", projectId);
        return "projects/diagram";
    }

    @GetMapping("/{projectId}/diagram/classdiagram")
    public String generateClassDiagram(@PathVariable Long projectId,
                                       @RequestParam String tool,
                                       Model model) {
        String script = projectsService.generateClassDiagram(projectId, tool);
        model.addAttribute("script", script);
        model.addAttribute("tool", tool);
        model.addAttribute("projectId", projectId);
        return "projects/diagram";
    }
}

