package com.sdepro2026.SDEPro0_12026.generation.classdiagram;

import java.util.List;

import com.sdepro2026.SDEPro0_12026.domain.CRCCard;
import com.sdepro2026.SDEPro0_12026.domain.Project;
public abstract class AbstractClassDiagramGenerator implements ClassDiagramStrategy {

    @Override
    public final String generateScript(Project project){
        StringBuilder sb = new StringBuilder();
        sb.append(generateHeader());
        sb.append(generateClasses(project.getCRCCards()));
        sb.append(generateAssociations(project.getCRCCards()));
        sb.append(generateFooter());
        return sb.toString();
    }

    protected abstract String generateHeader();
    protected abstract String generateClasses(List<CRCCard> cards);
    protected abstract String generateAssociations(List<CRCCard> cards);
    protected abstract String generateFooter();
}