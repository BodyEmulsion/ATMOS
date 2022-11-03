package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.services.BlockService;
import com.peltikhin.atmos.services.dto.BlockDto;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/block")
public class BlockController {
    private final BlockService blockService;

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlockDto> getBlockById(@PathVariable("id") Long id) {
        BlockDto result = this.blockService.getBlockById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BlockDto>> getBlocks(@RequestParam(name = "projectId", required = false, defaultValue = "0") @Parameter(description = "not required parameter that uses for specify project to which block belongs. If not specified, returns all user's blocks") Long projectId) {
        List<BlockDto> result = this.blockService.getBlocks(projectId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlockDto> createBlock(@RequestBody BlockDto blockDto) {
        BlockDto result = this.blockService.createBlock(blockDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BlockDto> updateBlock(@RequestBody BlockDto blockDto) {
        BlockDto result = this.blockService.updateBlock(blockDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable("id") Long id) {
        this.blockService.deleteBlock(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
