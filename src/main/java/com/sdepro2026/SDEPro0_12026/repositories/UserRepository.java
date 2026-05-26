package com.sdepro2026.SDEPro0_12026.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdepro2026.SDEPro0_12026.domain.User;





@Repository


public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByUsername(String username);
}

