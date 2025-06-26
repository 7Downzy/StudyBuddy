package ch.wiss.m223.StudyBuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.m223.StudyBuddy.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
