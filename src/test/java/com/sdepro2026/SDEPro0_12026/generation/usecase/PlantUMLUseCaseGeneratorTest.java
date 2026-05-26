package com.sdepro2026.SDEPro0_12026.generation.usecase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.sdepro2026.SDEPro0_12026.domain.Actor;
import com.sdepro2026.SDEPro0_12026.domain.Project;
import com.sdepro2026.SDEPro0_12026.domain.UseCase;

public class PlantUMLUseCaseGeneratorTest {

    @Test
    public void testGenerateScript_containsHeader() {
        Project project = buildProject();

        String script = new PlantUMLUseCaseGenerator().generateScript(project);

        assertTrue(script.contains("@startuml"));
        assertTrue(script.contains("@enduml"));
    }

    @Test
    public void testGenerateScript_containsActor() {
        Project project = buildProject();

        String script = new PlantUMLUseCaseGenerator().generateScript(project);

        assertTrue(script.contains("actor \"Admin\" as A_Admin"));
    }

    @Test
    public void testGenerateScript_containsUseCase() {
        Project project = buildProject();

        String script = new PlantUMLUseCaseGenerator().generateScript(project);

        assertTrue(script.contains("usecase \"Login\" as UC_Login"));
    }

    @Test
    public void testGenerateScript_containsAssociation() {
        Project project = buildProject();

        String script = new PlantUMLUseCaseGenerator().generateScript(project);

        assertTrue(script.contains("A_Admin --> UC_Login"));
    }

    private Project buildProject() {
        Actor actor = new Actor();
        actor.setName("Admin");

        UseCase uc = new UseCase();
        uc.setName("Login");
        uc.setActors(List.of(actor));

        Project project = new Project();
        project.setActors(List.of(actor));
        project.setUseCases(List.of(uc));

        return project;
    }
}