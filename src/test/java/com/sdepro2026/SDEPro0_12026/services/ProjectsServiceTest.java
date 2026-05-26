package com.sdepro2026.SDEPro0_12026.services;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sdepro2026.SDEPro0_12026.domain.CRCCard;
import com.sdepro2026.SDEPro0_12026.domain.Project;
import com.sdepro2026.SDEPro0_12026.domain.UseCase;
import com.sdepro2026.SDEPro0_12026.domain.User;
import com.sdepro2026.SDEPro0_12026.repositories.ActorRepository;
import com.sdepro2026.SDEPro0_12026.repositories.CRCCardRepository;
import com.sdepro2026.SDEPro0_12026.repositories.ProjectRepository;
import com.sdepro2026.SDEPro0_12026.repositories.UseCaseRepository;

@ExtendWith(MockitoExtension.class)
public class ProjectsServiceTest {

    @Mock private ProjectRepository projectRepository;
    @Mock private UseCaseRepository useCaseRepository;
    @Mock private CRCCardRepository crcCardRepository;
    @Mock private ActorRepository actorRepository;

    @InjectMocks
    private ProjectsService projectsService;

    @Test
    public void testCreateProject_savesAndReturns() {
        User owner = new User();
        Project saved = new Project();
        saved.setName("MyProject");
        when(projectRepository.save(Mockito.any())).thenReturn(saved);

        Project result = projectsService.createProject("MyProject", "desc", owner);

        assertEquals("MyProject", result.getName());
        verify(projectRepository, times(1)).save(Mockito.any());
    }

    @Test
    public void testGetProjectsForUser_returnsList() {
        User owner = new User();
        when(projectRepository.findByOwner(owner)).thenReturn(List.of(new Project(), new Project()));

        List<Project> result = projectsService.getProjectsForUser(owner);

        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteProject_callsRepository() {
        projectsService.deleteProject(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSaveUseCase_savesAndReturns() {
        UseCase uc = new UseCase();
        uc.setName("Login");
        when(useCaseRepository.save(uc)).thenReturn(uc);

        UseCase result = projectsService.saveUseCase(uc);

        assertEquals("Login", result.getName());
    }

    @Test
    public void testDeleteUseCase_callsRepository() {
        projectsService.deleteUseCase(5L);
        verify(useCaseRepository, times(1)).deleteById(5L);
    }

    @Test
    public void testSaveCRCCard_savesAndReturns() {
        CRCCard card = new CRCCard();
        card.setClassName("UserClass");
        when(crcCardRepository.save(card)).thenReturn(card);

        CRCCard result = projectsService.saveCRCCard(card);

        assertEquals("UserClass", result.getClassName());
    }

    @Test
    public void testFindProjectById_returnsProject() {
        Project p = new Project();
        p.setId(1L);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(p));

        Project result = projectsService.findProjectByID(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindProjectById_throwsWhenNotFound() {
        when(projectRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> projectsService.findProjectByID(99L));
    }
}
