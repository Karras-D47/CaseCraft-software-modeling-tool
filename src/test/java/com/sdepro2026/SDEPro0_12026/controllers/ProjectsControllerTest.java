package com.sdepro2026.SDEPro0_12026.controllers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import com.sdepro2026.SDEPro0_12026.domain.Project;
import com.sdepro2026.SDEPro0_12026.services.ProjectsService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProjectsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectsService projectsService;

    @TestConfiguration
    static class MockConfig {

        @Bean
        @Primary
        ProjectsService mockProjectsService() {
            return Mockito.mock(ProjectsService.class);
        }
    }

    @BeforeEach
    void setUp() {
        reset(projectsService);
    }

    @Test
    void testListProjects_returns200() throws Exception {
        Project project = new Project();
        project.setName("TestProject");
        project.setDescription("desc");

        when(projectsService.getProjectsForUser(any()))
                .thenReturn(List.of(project));

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(view().name("projects/list"))
                .andExpect(model().attributeExists("projects"))
                .andExpect(model().attributeExists("totalProjects"))
                .andExpect(model().attributeExists("totalUseCases"))
                .andExpect(model().attributeExists("totalCRCCards"))
                .andExpect(model().attributeExists("totalActors"));
    }

    @Test
    void testCreateProject_redirectsAfterSave() throws Exception {
        mockMvc.perform(post("/projects/create")
                        .with(csrf())
                        .param("name", "TestProject")
                        .param("description", "desc"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projects"));

        verify(projectsService).createProject(eq("TestProject"), eq("desc"), any());
    }

    @Test
    void testDeleteProject_redirectsAfterDelete() throws Exception {
        mockMvc.perform(post("/projects/1/delete")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projects"));

        verify(projectsService).deleteProject(1L);
    }

    @Test
    void testGenerateUseCaseDiagram_returns200() throws Exception {
        when(projectsService.generateUseCaseDiagram(1L, "plantuml"))
                .thenReturn("@startuml\n@enduml\n");

        mockMvc.perform(get("/projects/1/diagram/usecase")
                        .param("tool", "plantuml"))
                .andExpect(status().isOk())
                .andExpect(view().name("projects/diagram"))
                .andExpect(model().attributeExists("script"))
                .andExpect(model().attribute("tool", "plantuml"))
                .andExpect(model().attribute("projectId", 1L));
    }
}
