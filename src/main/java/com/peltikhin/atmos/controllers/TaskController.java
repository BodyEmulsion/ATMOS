package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.controllers.dto.TaskDto;
import com.peltikhin.atmos.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        TaskDto task = new TaskDto(taskService.getTaskById(id));
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<Collection<TaskDto>> getTasks(@RequestParam Long projectId) {
        Collection<TaskDto> tasks = taskService.getTasks(projectId).stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto task = new TaskDto(taskService.createTask(taskDto));
        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        TaskDto task = new TaskDto(taskService.updateTask(taskDto));
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
