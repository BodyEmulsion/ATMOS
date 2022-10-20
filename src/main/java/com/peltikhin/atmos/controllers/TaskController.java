package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.controllers.dto.TaskDto;
import com.peltikhin.atmos.controllers.mappers.TaskMapper;
import com.peltikhin.atmos.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        TaskDto task = taskMapper.toDTO(taskService.getTaskById(id));
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<Collection<TaskDto>> getTasks(@RequestParam Long projectId) {
        Collection<TaskDto> tasks = taskService.getTasks(projectId).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto task = taskMapper.toDTO(taskService.createTask(taskDto));
        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        TaskDto task = taskMapper.toDTO(taskService.updateTask(taskDto));
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
