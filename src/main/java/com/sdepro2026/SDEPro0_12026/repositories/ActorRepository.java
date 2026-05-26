package com.sdepro2026.SDEPro0_12026.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdepro2026.SDEPro0_12026.domain.Actor;



@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {}
