package com.sdepro2026.SDEPro0_12026.generation.usecase;


public class UseCaseDiagramGeneratorFactory{
    public static UseCaseDiagramStrategy create(String toolName){
        return switch (toolName.toLowerCase()) {
            case "plantuml" -> new PlantUMLUseCaseGenerator();
            case "nomnoml" -> new NomnomlUseCaseGenerator();
            default -> throw new IllegalArgumentException("Unknown use case tool:" + toolName);
        };
    }
}