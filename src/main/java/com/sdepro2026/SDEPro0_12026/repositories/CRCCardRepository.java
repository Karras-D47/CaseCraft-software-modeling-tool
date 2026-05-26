package com.sdepro2026.SDEPro0_12026.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdepro2026.SDEPro0_12026.domain.CRCCard;
import com.sdepro2026.SDEPro0_12026.domain.Project;


@Repository
public interface CRCCardRepository extends JpaRepository<CRCCard, Long> {
    List<CRCCard> findByProject(Project project);
}
