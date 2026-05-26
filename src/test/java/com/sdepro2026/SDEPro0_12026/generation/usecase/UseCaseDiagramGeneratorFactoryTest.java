package com.sdepro2026.SDEPro0_12026.generation.usecase;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class UseCaseDiagramGeneratorFactoryTest {

    @Test
    public void testCreate_returnsPlantUMLGenerator() {
        UseCaseDiagramStrategy strategy = UseCaseDiagramGeneratorFactory.create("plantuml");
        assertInstanceOf(PlantUMLUseCaseGenerator.class, strategy);
    }

    @Test
    public void testCreate_returnsNomnomlGenerator() {
        UseCaseDiagramStrategy strategy = UseCaseDiagramGeneratorFactory.create("nomnoml");
        assertInstanceOf(NomnomlUseCaseGenerator.class, strategy);
    }

    @Test
    public void testCreate_throwsOnUnknownTool() {
        assertThrows(IllegalArgumentException.class,
                () -> UseCaseDiagramGeneratorFactory.create("mermaid"));
    }
}