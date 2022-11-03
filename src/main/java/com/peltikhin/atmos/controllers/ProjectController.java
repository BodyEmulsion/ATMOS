package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.services.ProjectService;
import com.peltikhin.atmos.services.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> result = this.projectService.getAllProjects();
        //ToList() method didn't found dy IDE somehow, and I don't have time to deal with it. I'm really sorry, Sonarlint
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Long id) {
        ProjectDto result = this.projectService.getProjectById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<ProjectDto> createProject(String projectName) {
        ProjectDto result = this.projectService.createProject(projectName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping()
    ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        ProjectDto result = this.projectService.updateProject(projectDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProject(@PathVariable("id") Long id) {
        this.projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
