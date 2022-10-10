package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.controllers.dto.BlockDto;
import com.peltikhin.atmos.services.BlockService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/block")
public class BlockController {
    @Autowired
    private BlockService blockService;

    @GetMapping("/{id}")
    public ResponseEntity<BlockDto> getBlockById(@PathVariable("id") Long id) {
        BlockDto result = new BlockDto(this.blockService.getBlockById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BlockDto>> getBlocks(@RequestParam(name = "projectId", required = false, defaultValue = "0") @Parameter(description = "not required parameter that uses for specify project to which block belongs. If not specified, returns all user's blocks") Long projectId) {
        List<BlockDto> result = this.blockService.getBlocks(projectId).stream()
                .map(BlockDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlockDto> createBlock(@RequestBody BlockDto blockDto) {
        BlockDto result = new BlockDto(this.blockService.createBlock(blockDto.getProjectId(), blockDto.getTimeStart(), blockDto.getTimeEnd()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BlockDto> updateBlock(@RequestBody BlockDto blockDto) {
        BlockDto result = new BlockDto(this.blockService.updateBlock(blockDto.getId(), blockDto.getProjectId(), blockDto.getTimeStart(), blockDto.getTimeEnd()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable("id") Long id) {
        this.blockService.deleteBlock(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
