package com.sdepro2026.SDEPro0_12026.generation.usecase;

import java.util.List;

import com.sdepro2026.SDEPro0_12026.domain.Actor;
import com.sdepro2026.SDEPro0_12026.domain.UseCase;

public class NomnomlUseCaseGenerator extends AbstractUseCaseDiagramGenerator {

    @Override
    protected String generateHeader() {
        return "#direction: right\n"
             + "#actor: visual=actor\n"
             + "#usecase: visual=ellipse\n"
             + "\n";
    }

    @Override
    protected String generateActors(List<Actor> actors) {
        StringBuilder sb = new StringBuilder();

        for (Actor actor : actors) {
            sb.append("[<actor> ")
              .append(safeNomnomlText(actor.getName()))
              .append("]\n");
        }

        return sb.toString();
    }

    @Override
    protected String generateUseCases(List<UseCase> useCases) {
        StringBuilder sb = new StringBuilder();

        for (UseCase uc : useCases) {
            sb.append("[<usecase> ")
              .append(safeNomnomlText(uc.getName()))
              .append("]\n");
        }

        return sb.toString();
    }

    @Override
    protected String generateAssociations(List<UseCase> useCases) {
        StringBuilder sb = new StringBuilder();

        for (UseCase uc : useCases) {
            if (uc.getActors() == null) {
                continue;
            }

            for (Actor actor : uc.getActors()) {
                sb.append("[<actor> ")
                  .append(safeNomnomlText(actor.getName()))
                  .append("] -> [<usecase> ")
                  .append(safeNomnomlText(uc.getName()))
                  .append("]\n");
            }
        }

        return sb.toString();
    }

    @Override
    protected String generateFooter() {
        return "";
    }

    private String safeNomnomlText(String value) {
        if (value == null || value.isBlank()) {
            return "Unnamed";
        }

        return value
                .replace("[", "(")
                .replace("]", ")")
                .replace("|", "/")
                .replace("\r", " ")
                .replace("\n", " ")
                .trim();
    }
}