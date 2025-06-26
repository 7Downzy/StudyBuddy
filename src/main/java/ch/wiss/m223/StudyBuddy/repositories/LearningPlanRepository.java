package ch.wiss.m223.StudyBuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.m223.StudyBuddy.model.LearningPlan;

public interface LearningPlanRepository extends JpaRepository<LearningPlan, Long> {}
