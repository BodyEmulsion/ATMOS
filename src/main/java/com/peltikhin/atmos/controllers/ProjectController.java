package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.controllers.dto.ProjectDto;
import com.peltikhin.atmos.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping()
    ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> result = this.projectService.getAllProjects().stream()
                .map(ProjectDto::new)
                .collect(Collectors.toList());
        //ToList() method didn't found dy IDE somehow, and I don't have time to deal with it. I'm really sorry, Sonarlint
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Long id) {
        ProjectDto result = new ProjectDto(this.projectService.getProjectById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<ProjectDto> createProject(String projectName) {
        ProjectDto result = new ProjectDto(this.projectService.createProject(projectName));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping()
    ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        ProjectDto result = new ProjectDto(this.projectService.updateProject(projectDto.getId(), projectDto.getName()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProject(@PathVariable("id") Long id) {
        this.projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
