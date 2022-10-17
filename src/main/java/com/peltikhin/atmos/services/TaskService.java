package com.peltikhin.atmos.services;

import com.peltikhin.atmos.controllers.dto.TaskDto;
import com.peltikhin.atmos.jpa.models.Task;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.jpa.repositories.TaskRepository;
import com.peltikhin.atmos.services.exceptions.NotEnoughAuthoritiesException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final AuthService authService;
    private final ProjectService projectService;
    private final BlockService blockService;

    public TaskService(TaskRepository taskRepository, AuthService authService, ProjectService projectService, BlockService blockService) {
        this.taskRepository = taskRepository;
        this.authService = authService;
        this.projectService = projectService;
        this.blockService = blockService;
    }

    private static boolean isTaskBelongToUser(Task task, User user) {
        return task.getProject().getUser().getId().equals(user.getId());
    }

    private void validateUserAuthority(Task task) {
        //TODO Make it in query?
        var user = authService.getCurrentUser();
        if (!isTaskBelongToUser(task, user))
            throw new NotEnoughAuthoritiesException("Task doesn't belong to user");
    }

    public Task getTaskById(Long id) {
        Task task = taskRepository.findByIdOrError(id);
        validateUserAuthority(task);
        return task;
    }

    public Collection<Task> getTasks(Long projectId) {
        var project = projectService.getProjectById(projectId);
        return project.getTasks();
    }

    //TODO change the arguments to another class in order to reduce terrible coupling that I made here
    public Task createTask(TaskDto taskDto) {
        Task task = Task.builder()
                .name(taskDto.getName())
                .project(projectService.getProjectById(taskDto.getProjectId()))
                .created(Date.from(Instant.now()))
                .build();
        if (taskDto.getBlockId() != null) {
            task.plan(blockService.getBlockById(taskDto.getBlockId()));
        } else {
            task.unplan();
        }
        //Somehow it's return Task with projectId null
        return taskRepository.save(task);
    }

    //TODO change the argument to another class in order to reduce terrible coupling that I made here
    //TODO Optimize it somehow, there is too much requests
    public Task updateTask(TaskDto taskDto) {
        var task = taskRepository.findByIdOrError(taskDto.getId());
        validateUserAuthority(task);
        task.setName(taskDto.getName());
        if (!task.getProjectId().equals(taskDto.getProjectId()))
            task.setProject(projectService.getProjectById(taskDto.getProjectId()));
        if (taskDto.getBlockId() != null && task.getBlockId() != null && !task.getBlockId().equals(taskDto.getBlockId())) {
            if (taskDto.getBlockId() != null) {
                task.plan(blockService.getBlockById(taskDto.getBlockId()));
            } else {
                task.unplan();
            }
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        var task = taskRepository.findByIdOrError(taskId);
        validateUserAuthority(task);
        taskRepository.delete(task);
    }
}
