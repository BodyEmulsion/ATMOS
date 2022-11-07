package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.auth.CommonSecurityService;
import com.peltikhin.atmos.services.BlockService;
import com.peltikhin.atmos.services.dto.BlockDto;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/block")
public class BlockController {
    private final BlockService blockService;
    private final CommonSecurityService commonSecurityService;

    public BlockController(BlockService blockService, CommonSecurityService commonSecurityService) {
        this.blockService = blockService;
        this.commonSecurityService = commonSecurityService;
    }

    @PreAuthorize("@commonSecurityService.hasBlockOwnerRights(#id)")
    @GetMapping("/{id}")
    public ResponseEntity<BlockDto> getBlockById(@PathVariable("id") Long id) {
        BlockDto result = this.blockService.getBlockById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("#projectId > 0 ? @commonSecurityService.hasProjectOwnerRights(#projectId) : isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<BlockDto>> getBlocks(@RequestParam(name = "projectId", required = false, defaultValue = "0") @Parameter(description = "not required parameter that uses for specify project to which block belongs. If not specified, returns all user's blocks") Long projectId) {
        List<BlockDto> result = this.blockService.getBlocks(projectId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("@commonSecurityService.hasProjectOwnerRights(#blockDto.getProjectId())")
    @PostMapping
    public ResponseEntity<BlockDto> createBlock(@RequestBody BlockDto blockDto) {
        BlockDto result = this.blockService.createBlock(blockDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize(
            "@commonSecurityService.hasBlockOwnerRights(#blockDto.getId())" +
            "&& @commonSecurityService.hasProjectOwnerRights(#blockDto.getProjectId())"
    )
    @PutMapping
    public ResponseEntity<BlockDto> updateBlock(@RequestBody BlockDto blockDto) {
        BlockDto result = this.blockService.updateBlock(blockDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("@commonSecurityService.hasBlockOwnerRights(#id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable("id") Long id) {
        this.blockService.deleteBlock(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
