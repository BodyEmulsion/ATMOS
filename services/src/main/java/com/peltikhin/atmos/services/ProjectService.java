package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Project;
import com.peltikhin.atmos.jpa.repositories.ProjectRepository;
import com.peltikhin.atmos.services.dto.ProjectDto;
import com.peltikhin.atmos.services.dto.UserDto;
import com.peltikhin.atmos.services.mappers.ProjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final AuthService authService;
    private final ProjectMapper mapper;

    public ProjectService(ProjectRepository projectRepository, AuthService authService, ProjectMapper mapper) {
        this.projectRepository = projectRepository;
        this.authService = authService;
        this.mapper = mapper;
    }

    public ProjectDto getProjectById(Long id) {
        Project project = this.projectRepository.findByIdOrError(id);
        return mapper.toDto(project);
    }

    public ProjectDto createProject(String projectName) {
        //TODO Change method signature to Project or something else when project creation will require more atributes
        UserDto user = this.authService.getCurrentUser();
        var project = Project.builder()
                .name(projectName)
                .userId(user.getId())
                .build();
        return mapper.toDto(this.projectRepository.save(project));
    }

    public ProjectDto updateProject(ProjectDto projectDto) {
        Project project = this.projectRepository.findByIdOrError(projectDto.getId());
        project.setName(project.getName());
        return mapper.toDto(this.projectRepository.save(project));
    }

    public void deleteProject(Long id) {
        Project project = this.projectRepository.findByIdOrError(id);
        this.projectRepository.delete(project);
    }

    public List<ProjectDto> getAllProjects() {
        var user = this.authService.getCurrentUser();
        return this.projectRepository.findByUserId(user.getId()).stream().map(mapper::toDto).collect(Collectors.toList());
        //ToList() method didn't found dy IDE somehow, and I don't have time to deal with it. I'm really sorry, Sonarlint
    }
}
