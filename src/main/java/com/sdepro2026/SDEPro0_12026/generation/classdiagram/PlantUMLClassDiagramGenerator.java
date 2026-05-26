package com.sdepro2026.SDEPro0_12026.generation.classdiagram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sdepro2026.SDEPro0_12026.domain.CRCCard;

public class PlantUMLClassDiagramGenerator extends AbstractClassDiagramGenerator {

    @Override
    protected String generateHeader() {
        return "@startuml\n"
             + "hide empty members\n"
             + "skinparam classAttributeIconSize 0\n";
    }

    @Override
    protected String generateClasses(List<CRCCard> cards) {
        StringBuilder sb = new StringBuilder();

        for (CRCCard card : cards) {
            String className = safeLabel(card.getClassName());
            String classAlias = safeAlias("C", card.getId(), card.getClassName());

            sb.append("class \"")
              .append(className)
              .append("\" as ")
              .append(classAlias)
              .append(" {\n");

            if (card.getResponsibilities() != null && !card.getResponsibilities().isBlank()) {
                sb.append("  .. Responsibilities ..\n");

                for (String line : card.getResponsibilities().split("\\R")) {
                    if (!line.isBlank()) {
                        sb.append("  ")
                          .append(safeClassBodyLine(line))
                          .append("\n");
                    }
                }
            }

            sb.append("}\n");
        }

        return sb.toString();
    }

    @Override
    protected String generateAssociations(List<CRCCard> cards) {
        StringBuilder sb = new StringBuilder();

        Map<String, String> knownClassAliases = new HashMap<>();
        Set<String> declaredExternalClasses = new HashSet<>();

        for (CRCCard card : cards) {
            knownClassAliases.put(
                    normalize(card.getClassName()),
                    safeAlias("C", card.getId(), card.getClassName())
            );
        }

        for (CRCCard card : cards) {
            String fromAlias = safeAlias("C", card.getId(), card.getClassName());

            if (card.getCollaborations() == null || card.getCollaborations().isBlank()) {
                continue;
            }

            for (String collab : card.getCollaborations().split(",")) {
                String collaboratorName = collab.trim();

                if (collaboratorName.isBlank()) {
                    continue;
                }

                String collaboratorKey = normalize(collaboratorName);
                String toAlias = knownClassAliases.get(collaboratorKey);

                if (toAlias == null) {
                    toAlias = safeAlias("C", null, collaboratorName);

                    if (!declaredExternalClasses.contains(toAlias)) {
                        sb.append("class \"")
                          .append(safeLabel(collaboratorName))
                          .append("\" as ")
                          .append(toAlias)
                          .append("\n");

                        declaredExternalClasses.add(toAlias);
                    }
                }

                sb.append(fromAlias)
                  .append(" --> ")
                  .append(toAlias)
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

    private String safeClassBodyLine(String value) {
        return value
                .replace("{", "(")
                .replace("}", ")")
                .replace("\"", "'")
                .trim();
    }

    private String normalize(String value) {
        if (value == null) {
            return "";
        }

        return value.trim().toLowerCase();
    }
}