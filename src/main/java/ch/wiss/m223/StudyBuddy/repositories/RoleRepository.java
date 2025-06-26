package ch.wiss.m223.StudyBuddy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.m223.StudyBuddy.model.ERole;
import ch.wiss.m223.StudyBuddy.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   Optional<Role> findByName(ERole name);

    
}
