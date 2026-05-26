package com.sdepro2026.SDEPro0_12026.generation.usecase;

import java.util.List;

import com.sdepro2026.SDEPro0_12026.domain.Actor;
import com.sdepro2026.SDEPro0_12026.domain.UseCase;

public class PlantUMLUseCaseGenerator extends AbstractUseCaseDiagramGenerator {

    @Override
    protected String generateHeader() {
        return "@startuml\n";
    }

    @Override
    protected String generateActors(List<Actor> actors) {
        StringBuilder sb = new StringBuilder();

        for (Actor actor : actors) {
            String actorName = safeLabel(actor.getName());
            String actorAlias = safeAlias("A", actor.getId(), actor.getName());

            sb.append("actor \"")
              .append(actorName)
              .append("\" as ")
              .append(actorAlias)
              .append("\n");
        }

        return sb.toString();
    }

    @Override
    protected String generateUseCases(List<UseCase> useCases) {
        StringBuilder sb = new StringBuilder();

        for (UseCase uc : useCases) {
            String useCaseName = safeLabel(uc.getName());
            String useCaseAlias = safeAlias("UC", uc.getId(), uc.getName());

            sb.append("usecase \"")
              .append(useCaseName)
              .append("\" as ")
              .append(useCaseAlias)
              .append("\n");
        }

        return sb.toString();
    }

    @Override
    protected String generateAssociations(List<UseCase> useCases) {
        StringBuilder sb = new StringBuilder();

        for (UseCase uc : useCases) {
            String useCaseAlias = safeAlias("UC", uc.getId(), uc.getName());

            if (uc.getActors() == null) {
                continue;
            }

            for (Actor actor : uc.getActors()) {
                String actorAlias = safeAlias("A", actor.getId(), actor.getName());

                sb.append(actorAlias)
                  .append(" --> ")
                  .append(useCaseAlias)
                  .append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    protected String generateFooter() {
        return "@enduml\n";
    }

    private String safeAlias(String prefix, Long id, String value) {
        String text = value == null ? "Unnamed" : value.trim();

        String cleaned = text
                .replaceAll("[^A-Za-z0-9]+", "_")
                .replaceAll("_+", "_")
                .replaceAll("^_+", "")
                .replaceAll("_+$", "");

        if (cleaned.isBlank()) {
            cleaned = "Unnamed";
        }

        if (id != null) {
            return prefix + "_" + id + "_" + cleaned;
        }

        return prefix + "_" + cleaned;
    }

    private String safeLabel(String value) {
        if (value == null || value.isBlank()) {
            return "Unnamed";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}