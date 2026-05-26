package com.sdepro2026.SDEPro0_12026.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdepro2026.SDEPro0_12026.domain.Project;
import com.sdepro2026.SDEPro0_12026.domain.UseCase;



@Repository
public interface UseCaseRepository extends JpaRepository<UseCase, Long> {
    List<UseCase> findByProject(Project project);
}