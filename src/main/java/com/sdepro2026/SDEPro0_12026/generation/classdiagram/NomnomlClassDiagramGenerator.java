package com.sdepro2026.SDEPro0_12026.generation.classdiagram;

import java.util.List;

import com.sdepro2026.SDEPro0_12026.domain.CRCCard;

public class NomnomlClassDiagramGenerator extends AbstractClassDiagramGenerator {

    @Override
    protected String generateHeader() {
        return "#direction: right\n\n";
    }

    @Override
    protected String generateClasses(List<CRCCard> cards) {
        StringBuilder sb = new StringBuilder();

        for (CRCCard card : cards) {
            sb.append("[")
              .append(safeNomnomlText(card.getClassName()));

            if (card.getResponsibilities() != null && !card.getResponsibilities().isBlank()) {
                sb.append("|")
                  .append(safeResponsibilities(card.getResponsibilities()));
            }

            sb.append("]\n");
        }

        return sb.toString();
    }

    @Override
    protected String generateAssociations(List<CRCCard> cards) {
        StringBuilder sb = new StringBuilder();

        for (CRCCard card : cards) {
            if (card.getCollaborations() == null || card.getCollaborations().isBlank()) {
                continue;
            }

            for (String collab : card.getCollaborations().split(",")) {
                String collaboratorName = collab.trim();

                if (collaboratorName.isBlank()) {
                    continue;
                }

                sb.append("[")
                  .append(safeNomnomlText(card.getClassName()))
                  .append("] -> [")
                  .append(safeNomnomlText(collaboratorName))
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

    private String safeResponsibilities(String value) {
        if (value == null || value.isBlank()) {
            return "";
        }

        return value
                .replace("[", "(")
                .replace("]", ")")
                .replace("|", "/")
                .replace("\r", " ")
                .replace("\n", "; ")
                .trim();
    }
}