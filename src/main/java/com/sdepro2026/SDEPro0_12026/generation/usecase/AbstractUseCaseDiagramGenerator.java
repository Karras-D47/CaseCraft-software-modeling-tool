package com.sdepro2026.SDEPro0_12026.generation.usecase;
import com.sdepro2026.SDEPro0_12026.domain.Actor;
import com.sdepro2026.SDEPro0_12026.domain.Project;
import com.sdepro2026.SDEPro0_12026.domain.UseCase;

import java.util.List;

public abstract class AbstractUseCaseDiagramGenerator implements UseCaseDiagramStrategy {
    @Override
    public final String generateScript(Project project){
        StringBuilder sb = new StringBuilder();
        sb.append(generateHeader());
        sb.append(generateActors(project.getActors()));
        sb.append(generateUseCases(project.getUseCases()));
        sb.append(generateAssociations(project.getUseCases()));
        sb.append(generateFooter());
        return sb.toString();

    }

    protected abstract String generateHeader();
    protected abstract String generateActors(List<Actor> actors);
    protected abstract String generateUseCases(List<UseCase> useCases);
    protected abstract String generateAssociations(List<UseCase> useCases);
    protected abstract String generateFooter();
}
