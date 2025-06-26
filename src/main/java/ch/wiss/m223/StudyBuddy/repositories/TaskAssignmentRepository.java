package ch.wiss.m223.StudyBuddy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.m223.StudyBuddy.model.TaskAssignment;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    List<TaskAssignment> findByStudentId(Long studentId);
}
