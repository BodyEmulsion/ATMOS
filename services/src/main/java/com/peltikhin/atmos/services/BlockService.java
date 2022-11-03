package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.Block;
import com.peltikhin.atmos.jpa.repositories.BlockRepository;
import com.peltikhin.atmos.services.dto.BlockDto;
import com.peltikhin.atmos.services.mappers.BlockMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockService {
    private final BlockRepository blockRepository;
    private final AuthService authService;
    private final ValidationService validationService;
    private final BlockMapper mapper;

    public BlockService(BlockRepository blockRepository, AuthService authService, ValidationService validationService, BlockMapper mapper) {
        this.blockRepository = blockRepository;
        this.authService = authService;
        this.validationService = validationService;
        this.mapper = mapper;
    }

    public BlockDto getBlockById(Long id) {
        Block block = this.blockRepository.findByIdOrError(id);
        validationService.validateUserAuthority(block);
        return mapper.toDto(block);
    }

    public List<BlockDto> getBlocks(Long projectId) {
        Collection<Block> blocks;
        if (projectId.equals(0L))
            blocks = this.blockRepository.findByProject_User_Id(this.authService.getCurrentUser().getId());
        else {
            blocks = this.blockRepository.findByProjectId(projectId);
        }
        return blocks.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public BlockDto createBlock(BlockDto blockDto) {
        Block block = Block.builder()
                .timeStart(blockDto.getTimeStart())
                .timeEnd(blockDto.getTimeEnd())
                .projectId(blockDto.getProjectId())
                .build();
        return mapper.toDto(this.blockRepository.save(block));
    }

    public BlockDto updateBlock(BlockDto blockDto) {
        Block block = this.blockRepository.findByIdOrError(blockDto.getId());
        validationService.validateUserAuthority(block);
        block.setProjectId(blockDto.getProjectId());
        block.setTimeStart(blockDto.getTimeStart());
        block.setTimeEnd(blockDto.getTimeEnd());
        return mapper.toDto(this.blockRepository.save(block));
    }

    public void deleteBlock(Long id) {
        var block = this.blockRepository.findByIdOrError(id);
        validationService.validateUserAuthority(block);
        this.blockRepository.delete(block);
    }
}
