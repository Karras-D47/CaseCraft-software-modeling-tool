package com.sdepro2026.SDEPro0_12026.generation.classdiagram;

public class ClassDiagramGeneratorFactory {

    public static ClassDiagramStrategy create(String toolName) {
        return switch (toolName.toLowerCase()) {
            case "plantuml" -> new PlantUMLClassDiagramGenerator();
            case "nomnoml" -> new NomnomlClassDiagramGenerator();
            default -> throw new IllegalArgumentException("Unknown class diagram tool:" + toolName);
        };
    }
}