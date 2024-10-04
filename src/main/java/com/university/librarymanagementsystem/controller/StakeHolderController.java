package com.university.librarymanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.StakeholdersDto;
import com.university.librarymanagementsystem.service.StakeHolderService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/stakeholders")
public class StakeHolderController {

    private StakeHolderService stakeHolderService;

    // Build Add Stakeholder REST API
    @PostMapping
    public ResponseEntity<StakeholdersDto> createStakeHolder(@RequestBody StakeholdersDto stakeholdersDto) {
        StakeholdersDto savedStakeholders = stakeHolderService.createStakeholder(stakeholdersDto);
        return new ResponseEntity<>(savedStakeholders, HttpStatus.CREATED);
    }

    // Build Get Stakeholder REST API
    @GetMapping("{stakeHolderId}")
    public ResponseEntity<StakeholdersDto> getStakeholderId(@PathVariable("stakeHolderId") String id) {
        StakeholdersDto stakeholdersDto = stakeHolderService.getStakeholderById(id);
        return ResponseEntity.ok(stakeholdersDto);
    }

}
