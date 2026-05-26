package com.sdepro2026.SDEPro0_12026.generation.classdiagram;



import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ClassDiagramGeneratorFactoryTest {

    @Test
    public void testCreate_returnsPlantUMLGenerator() {
        ClassDiagramStrategy strategy = ClassDiagramGeneratorFactory.create("plantuml");
        assertInstanceOf(PlantUMLClassDiagramGenerator.class, strategy);
    }

    @Test
    public void testCreate_returnsNomnomlGenerator() {
        ClassDiagramStrategy strategy = ClassDiagramGeneratorFactory.create("nomnoml");
        assertInstanceOf(NomnomlClassDiagramGenerator.class, strategy);
    }

    @Test
    public void testCreate_throwsOnUnknownTool() {
        assertThrows(IllegalArgumentException.class,
                () -> ClassDiagramGeneratorFactory.create("mermaid"));
    }
}
