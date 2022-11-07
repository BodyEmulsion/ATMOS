package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.auth.CommonSecurityService;
import com.peltikhin.atmos.services.ProjectService;
import com.peltikhin.atmos.services.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;
    private final CommonSecurityService commonSecurityService;

    public ProjectController(ProjectService projectService, CommonSecurityService commonSecurityService) {
        this.projectService = projectService;
        this.commonSecurityService = commonSecurityService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> result = this.projectService.getAllProjects();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("@commonSecurityService.hasProjectOwnerRights(#id)")
    @GetMapping("/{id}")
    ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Long id) {
        ProjectDto result = this.projectService.getProjectById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping()
    ResponseEntity<ProjectDto> createProject(String projectName) {
        ProjectDto result = this.projectService.createProject(projectName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("@commonSecurityService.hasProjectOwnerRights(#projectDto.getId())")
    @PutMapping()
    ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        ProjectDto result = this.projectService.updateProject(projectDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("@commonSecurityService.hasProjectOwnerRights(#id)")
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProject(@PathVariable("id") Long id) {
        this.projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
