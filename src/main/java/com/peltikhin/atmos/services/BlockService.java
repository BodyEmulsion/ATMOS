package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Block;
import com.peltikhin.atmos.jpa.repositories.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class BlockService {
    private final BlockRepository blockRepository;
    private final AuthService authService;
    private final ProjectService projectService;
    private final ValidationService validationService;

    public BlockService(BlockRepository blockRepository, AuthService authService, ProjectService projectService, ValidationService validationService) {
        this.blockRepository = blockRepository;
        this.authService = authService;
        this.projectService = projectService;
        this.validationService = validationService;
    }

    public Block getBlockById(Long id) {
        Block block = this.blockRepository.findByIdOrError(id);
        validationService.validateUserAuthority(block);
        return block;
    }

    public Collection<Block> getBlocks(Long projectId) {
        Collection<Block> blocks;
        if (projectId.equals(0L))
            blocks = this.blockRepository.findByProject_User(this.authService.getCurrentUser());
        else {
            var project = this.projectService.getProjectById(projectId);
            blocks = this.blockRepository.findByProject(project);
        }
        return blocks;
    }

    public Block createBlock(Long projectId, Date timeStart, Date timeEnd) {
        Block block = Block.builder()
                .timeStart(timeStart)
                .timeEnd(timeEnd)
                .project(this.projectService.getProjectById(projectId))
                .build();
        return this.blockRepository.save(block);
    }

    public Block updateBlock(Long id, Long projectId, Date timeStart, Date timeEnd) {
        Block block = this.blockRepository.findByIdOrError(id);
        validationService.validateUserAuthority(block);
        block.setProject(this.projectService.getProjectById(projectId));
        block.setTimeStart(timeStart);
        block.setTimeEnd(timeEnd);
        return this.blockRepository.save(block);
    }

    public void deleteBlock(Long id) {
        var block = this.blockRepository.findByIdOrError(id);
        validationService.validateUserAuthority(block);
        this.blockRepository.delete(block);
    }
}
