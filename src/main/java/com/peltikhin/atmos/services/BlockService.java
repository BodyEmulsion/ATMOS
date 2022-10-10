package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Block;
import com.peltikhin.atmos.jpa.repositories.BlockRepository;
import com.peltikhin.atmos.services.exceptions.BlockNotFoundException;
import com.peltikhin.atmos.services.exceptions.NotEnoughAuthoritiesException;
import com.peltikhin.atmos.services.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class BlockService {
    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private ProjectService projectService;


    public Block getBlockById(Long id) {
        Block block = tryToGetBlock(id);
        validateUserAuthority(block);
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
        Block block = tryToGetBlock(id);
        validateUserAuthority(block);
        block.setProject(this.projectService.getProjectById(projectId));
        block.setTimeStart(timeStart);
        block.setTimeEnd(timeEnd);
        return this.blockRepository.save(block);
    }

    private Block tryToGetBlock(Long id) {
        var result = this.blockRepository.findById(id);
        if (result.isEmpty())
            throw new BlockNotFoundException(id);
        return result.get();
    }

    private void validateUserAuthority(Block block) {
        UserInfo userInfo = this.authService.getCurrentUserInfo();
        if (!isBlockBelongToUser(block, userInfo))
            throw new NotEnoughAuthoritiesException("Block does not belong to user");
    }

    private boolean isBlockBelongToUser(Block block, UserInfo userInfo) {
        return userInfo.getId().equals(block.getProject().getUser().getId());
    }

    public void deleteBlock(Long id) {
        var block = tryToGetBlock(id);
        validateUserAuthority(block);
        this.blockRepository.delete(block);
    }
}
