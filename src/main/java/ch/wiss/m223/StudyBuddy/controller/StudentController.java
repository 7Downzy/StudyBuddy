package ch.wiss.m223.StudyBuddy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m223.StudyBuddy.model.Comment;
import ch.wiss.m223.StudyBuddy.model.Task;
import ch.wiss.m223.StudyBuddy.model.TaskAssignment;
import ch.wiss.m223.StudyBuddy.model.TaskStatus;
import ch.wiss.m223.StudyBuddy.model.User;
import ch.wiss.m223.StudyBuddy.repositories.CommentRepository;
import ch.wiss.m223.StudyBuddy.repositories.TaskAssignmentRepository;
import ch.wiss.m223.StudyBuddy.repositories.TaskRepository;
import ch.wiss.m223.StudyBuddy.repositories.UserRepository;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {
    @Autowired
    TaskAssignmentRepository assignRepo;
    @Autowired
    TaskRepository taskRepo;
    @Autowired
    CommentRepository commentRepo;
    @Autowired
    UserRepository userRepo;

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/tasks")
    public List<TaskAssignment> myTasks(Authentication auth) {
        User user = userRepo.findByUsername(auth.getName()).orElseThrow();
        return assignRepo.findByStudentId(user.getId());
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/assignments/{id}/status")
    public TaskAssignment updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        TaskAssignment a = assignRepo.findById(id).orElseThrow();
        TaskStatus status = TaskStatus.valueOf(body.get("status"));
        a.setStatus(status);
        return assignRepo.save(a);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/tasks/{taskId}/comments")
    public Comment addComment(@PathVariable Long taskId, @RequestBody Map<String, String> body, Authentication auth) {
        Task task = taskRepo.findById(taskId).orElseThrow();
        User user = userRepo.findByUsername(auth.getName()).orElseThrow();
        Comment c = new Comment();
        c.setTask(task);
        c.setAuthor(user);
        c.setText(body.get("text"));
        return commentRepo.save(c);
    }
}
