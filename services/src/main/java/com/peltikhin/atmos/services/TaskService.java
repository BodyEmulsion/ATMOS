package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Task;
import com.peltikhin.atmos.jpa.repositories.TaskRepository;
import com.peltikhin.atmos.services.dto.TaskDto;
import com.peltikhin.atmos.services.mappers.TaskMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository taskRepository, TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findByIdOrError(id);
        return mapper.toDTO(task);
    }

    public List<TaskDto> getTasks(Long projectId) {
        Collection<Task> tasks = taskRepository.findByProjectId(projectId);
        return tasks.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = Task.builder()
                .name(taskDto.getName())
                .projectId(taskDto.getProjectId())
                .created(Date.from(Instant.now()))
                .blockId(taskDto.getBlockId())
                .build();
        //Somehow it's return Task with projectId null
        return mapper.toDTO(taskRepository.save(task));
    }

    //TODO change the argument to another class in order to reduce terrible coupling that I made here
    public TaskDto updateTask(TaskDto taskDto) {
        //TODO change it to mapper.toEntity() after add annotation based validation?
        var task = taskRepository.findByIdOrError(taskDto.getId());
        task.setName(taskDto.getName());
        task.setProjectId(taskDto.getProjectId());
        task.setBlockId(taskDto.getProjectId());
        return mapper.toDTO(taskRepository.save(task));
    }

    public void deleteTask(Long taskId) {
        var task = taskRepository.findByIdOrError(taskId);
        taskRepository.delete(task);
    }
}
