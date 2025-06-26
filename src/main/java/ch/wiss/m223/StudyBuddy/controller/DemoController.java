package ch.wiss.m223.StudyBuddy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;


@RestController
public class DemoController {

    List<String> list = new ArrayList<>();

    @CrossOrigin
    @GetMapping("/public")
    public ResponseEntity<List<String>> publicEndpoint() {
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @PostMapping("/public")
    public ResponseEntity<List<String>> postPublicEndpoint(@RequestBody String message) {
        list.add(message);
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @GetMapping("/private")
    public ResponseEntity<String> privateEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok("Welcome " + auth.getName() + ", This is a private endpoint.");
    }
}