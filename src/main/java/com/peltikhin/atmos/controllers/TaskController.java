package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.auth.CommonSecurityService;
import com.peltikhin.atmos.services.TaskService;
import com.peltikhin.atmos.services.dto.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;
    private final CommonSecurityService commonSecurityService;

    public TaskController(TaskService taskService, CommonSecurityService commonSecurityService) {
        this.taskService = taskService;
        this.commonSecurityService = commonSecurityService;
    }

    @PreAuthorize("@commonSecurityService.hasTaskOwnerRights(#id)")
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        TaskDto task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PreAuthorize("@commonSecurityService.hasProjectOwnerRights(#projectId)")
    @GetMapping
    public ResponseEntity<Collection<TaskDto>> getTasks(@RequestParam Long projectId) {
        Collection<TaskDto> tasks = taskService.getTasks(projectId);
        return ResponseEntity.ok(tasks);
    }

    //TODO: create custom anotation or combine this logic into one method or do something else in order to simplify code that looks like this
    @PreAuthorize(
            "isAuthenticated()" +
            "&& @commonSecurityService.hasProjectOwnerRights(#taskDto.getProjectId())" +
            "&& #taskDto.getBlockId() != null ? @commonSecurityService.hasBlockOwnerRights(#taskDto.getBlockId()) : true"
    )
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto task = taskService.createTask(taskDto);
        return ResponseEntity.ok(task);
    }

    @PreAuthorize(
            "@commonSecurityService.hasTaskOwnerRights(#taskDto.getId())" +
            "&& @commonSecurityService.hasProjectOwnerRights(#taskDto.getProjectId())" +
            "&& #taskDto.getBlockId() != null ? @commonSecurityService.hasBlockOwnerRights(#taskDto.getBlockId()) : true"
    )
    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        TaskDto task = taskService.updateTask(taskDto);
        return ResponseEntity.ok(task);
    }

    @PreAuthorize("@commonSecurityService.hasTaskOwnerRights(#id)")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
