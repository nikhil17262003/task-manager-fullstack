package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Optional;


import java.util.List;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasks")

public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // ✅ CREATE TASK
    @PostMapping("/{email}")
    public ResponseEntity<?> createTask(
            @PathVariable String email,
            @RequestBody Task task) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("User not found with email: " + email);
        }

        User user = userOptional.get();

        task.setUser(user);
        task.setStatus("PENDING");

        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }

    // ✅ UPDATE STATUS
    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestParam String status) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(status);
        return taskRepository.save(task);
    }

    // ✅ DELETE TASK
    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskRepository.deleteById(taskId);


    }
    @GetMapping("/{email}")
    public ResponseEntity<?> getTasksByUser(@PathVariable String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("User not found with email: " + email);
        }

        User user = optionalUser.get();

        List<Task> tasks = taskRepository.findByUser(user);

        return ResponseEntity.ok(tasks);
    }
}