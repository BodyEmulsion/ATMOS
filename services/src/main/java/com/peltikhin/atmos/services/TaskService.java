package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Task;
import com.peltikhin.atmos.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ValidationService validationService;
    private final ProjectService projectService;
    private final BlockService blockService;

    public TaskService(TaskRepository taskRepository, ValidationService validationService, ProjectService projectService, BlockService blockService) {
        this.taskRepository = taskRepository;
        this.validationService = validationService;
        this.projectService = projectService;
        this.blockService = blockService;
    }

    public Task getTaskById(Long id) {
        Task task = taskRepository.findByIdOrError(id);
        validationService.validateUserAuthority(task);
        return task;
    }

    public Collection<Task> getTasks(Long projectId) {
        var project = projectService.getProjectById(projectId);
        return project.getTasks();
    }

    //TODO change the arguments to another class in order to reduce terrible coupling that I made here
    public Task createTask(String name, Long projectId, Long blockId, boolean isPlanned) {
        Task task = Task.builder()
                .name(name)
                .project(projectService.getProjectById(projectId))
                .created(Date.from(Instant.now()))
                .build();
        if (isPlanned) {
            task.setBlock(blockService.getBlockById(blockId));
        }
        //Somehow it's return Task with projectId null
        return taskRepository.save(task);
    }

    //TODO change the argument to another class in order to reduce terrible coupling that I made here
    public Task updateTask(Long id, String name, Long projectId, Long blockId, boolean isPlanned) {
        var task = taskRepository.findByIdOrError(id);
        validationService.validateUserAuthority(task);
        task.setName(name);
        if (!task.getProjectId().equals(projectId))
            task.setProject(projectService.getProjectById(projectId));
        updateTaskBlockIfNeeded(task, isPlanned, blockId);
        return taskRepository.save(task);
    }

    //TODO It may be simplified(to one line...) by making block_id field updatable and insertable, I think
    private void updateTaskBlockIfNeeded(Task task, boolean taskShouldBePlanned, Long oldBlockId) {
        boolean taskIsPlannedNow = task.isPlanned();
        boolean taskPlannedAndNotEqual =
                taskShouldBePlanned &&
                taskIsPlannedNow &&
                !task.getBlockId().equals(oldBlockId);
        if(taskPlannedAndNotEqual || taskShouldBePlanned && !taskIsPlannedNow)
            task.setBlock(blockService.getBlockById(oldBlockId));
        if(!taskShouldBePlanned && taskIsPlannedNow)
            task.setBlock(null);
    }

    public void deleteTask(Long taskId) {
        var task = taskRepository.findByIdOrError(taskId);
        validationService.validateUserAuthority(task);
        taskRepository.delete(task);
    }
}
