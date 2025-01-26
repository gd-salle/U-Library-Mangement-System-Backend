package com.university.librarymanagementsystem.controller.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.catalog.WeedingCriteriaDTO;
import com.university.librarymanagementsystem.service.catalog.WeedingCriteriaService;

@RestController
@RequestMapping("/admin/weeding-criteria")
public class WeedingCriteriaController {

    @Autowired
    private WeedingCriteriaService weedingCriteriaService;

    @PostMapping
    public ResponseEntity<WeedingCriteriaDTO> createWeedingCriteria(
            @RequestBody WeedingCriteriaDTO weedingCriteriaDTO) {
        return ResponseEntity.ok(
                weedingCriteriaService.createWeedingCriteria(weedingCriteriaDTO));
    }

    @PutMapping("/update-criteria")
    public ResponseEntity<WeedingCriteriaDTO> updateWeedingCriteria(
            @RequestBody WeedingCriteriaDTO weedingCriteriaDTO) {
        return ResponseEntity.ok(weedingCriteriaService
                .updateWeedingCriteria(weedingCriteriaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeedingCriteria(@PathVariable Integer id) {
        weedingCriteriaService.deleteWeedingCriteria(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<WeedingCriteriaDTO>> getAllWeedingCriteria() {
        return ResponseEntity.ok(weedingCriteriaService.getAllWeedingCriteria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeedingCriteriaDTO> getWeedingCriteriaById(@PathVariable Integer id) {
        WeedingCriteriaDTO weedingCriteriaDTO = weedingCriteriaService.getWeedingCriteriaById(id);
        return ResponseEntity.ok(weedingCriteriaDTO); // Return the response with status 200
    }

}
