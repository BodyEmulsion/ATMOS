package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Project;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.jpa.repositories.ProjectRepository;
import com.peltikhin.atmos.services.exceptions.NotEnoughAuthoritiesException;
import com.peltikhin.atmos.services.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private AuthService authService;

    private static boolean isProjectBelongToUser(Project project, User user) {
        return user.getId().equals(project.getUser().getId());
    }

    public Project getProjectById(Long id) {
        Project project = tryToGetProject(id);
        validateUserAuthority(project);
        return project;
    }

    private Project tryToGetProject(Long id) {
        var result = this.projectRepository.findById(id);
        if (result.isEmpty())
            throw new ProjectNotFoundException(id);
        return result.get();
    }

    //TODO Move validation in another class?
    private void validateUserAuthority(Project project) {
        User user = this.authService.getCurrentUser();
        if (!isProjectBelongToUser(project, user))
            throw new NotEnoughAuthoritiesException("Project does not belong to user");
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
        var project = tryToGetProject(projectId);
        project.setName(name);
        validateUserAuthority(project);
        return this.projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = tryToGetProject(id);
        validateUserAuthority(project);
        this.projectRepository.delete(project);
    }

    public Collection<Project> getAllProjects() {
        var user = this.authService.getCurrentUser();
        //TODO make sure that it's ok, because I think I should be able to read it from user entity, but It requires to refresh entity by EntityManager
        return this.projectRepository.findByUser(user);
    }
}
