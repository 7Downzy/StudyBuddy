package ch.wiss.m223.StudyBuddy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m223.StudyBuddy.model.LearningPlan;
import ch.wiss.m223.StudyBuddy.model.StudyClass;
import ch.wiss.m223.StudyBuddy.model.Task;
import ch.wiss.m223.StudyBuddy.model.TaskAssignment;
import ch.wiss.m223.StudyBuddy.model.TaskStatus;
import ch.wiss.m223.StudyBuddy.model.User;
import ch.wiss.m223.StudyBuddy.repositories.LearningPlanRepository;
import ch.wiss.m223.StudyBuddy.repositories.StudyClassRepository;
import ch.wiss.m223.StudyBuddy.repositories.TaskAssignmentRepository;
import ch.wiss.m223.StudyBuddy.repositories.TaskRepository;
import ch.wiss.m223.StudyBuddy.repositories.UserRepository;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    StudyClassRepository classRepo;
    @Autowired
    LearningPlanRepository planRepo;
    @Autowired
    TaskRepository taskRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    TaskAssignmentRepository assignRepo;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/classes")
    public StudyClass createClass(@RequestBody StudyClass c) {
        return classRepo.save(c);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/classes/{classId}/learningplans")
    public LearningPlan createPlan(@PathVariable Long classId, @RequestBody LearningPlan plan) {
        StudyClass sc = classRepo.findById(classId).orElseThrow();
        plan.setStudyClass(sc);
        return planRepo.save(plan);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/learningplans/{planId}/tasks")
    public Task createTask(@PathVariable Long planId, @RequestBody Task task) {
        LearningPlan plan = planRepo.findById(planId).orElseThrow();
        task.setLearningPlan(plan);
        return taskRepo.save(task);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/tasks/{taskId}/assign")
    public TaskAssignment assignTask(@PathVariable Long taskId, @RequestBody Map<String, Long> body) {
        Task task = taskRepo.findById(taskId).orElseThrow();
        Long userId = body.get("userId");
        User user = userRepo.findById(userId).orElseThrow();
        TaskAssignment a = new TaskAssignment();
        a.setTask(task);
        a.setStudent(user);
        a.setStatus(TaskStatus.TODO);
        return assignRepo.save(a);
    }
}
