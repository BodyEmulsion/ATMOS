package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Project;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final AuthService authService;
    private final ValidationService validationService;

    public ProjectService(ProjectRepository projectRepository, AuthService authService, ValidationService validationService) {
        this.projectRepository = projectRepository;
        this.authService = authService;
        this.validationService = validationService;
    }

    public Project getProjectById(Long id) {
        Project project = this.projectRepository.findByIdOrError(id);
        validationService.validateUserAuthority(project);
        return project;
    }

    public Project createProject(String projectName) {
        //TODO Change method signature to Project or something else when project creation will require more atributes
        User user = this.authService.getCurrentUser();
        var project = Project.builder()
                .name(projectName)
                .user(user)
                .build();
        return this.projectRepository.save(project);
    }

    public Project updateProject(Long projectId, String name) {
        //TODO Change method signature to Project or something else when project updation will require more atributes
        var project = this.projectRepository.findByIdOrError(projectId);
        project.setName(name);
        validationService.validateUserAuthority(project);
        return this.projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = this.projectRepository.findByIdOrError(id);
        validationService.validateUserAuthority(project);
        this.projectRepository.delete(project);
    }

    public Collection<Project> getAllProjects() {
        var user = this.authService.getCurrentUser();
        //TODO make sure that it's ok, because I think I should be able to read it from user entity, but It requires to refresh entity by EntityManager
        return this.projectRepository.findByUser(user);
    }
}
