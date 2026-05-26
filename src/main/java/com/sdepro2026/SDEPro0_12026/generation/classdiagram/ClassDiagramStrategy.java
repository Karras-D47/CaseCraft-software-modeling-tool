package com.sdepro2026.SDEPro0_12026.generation.classdiagram;

import com.sdepro2026.SDEPro0_12026.domain.Project;

public interface ClassDiagramStrategy{
    String generateScript(Project project);
}