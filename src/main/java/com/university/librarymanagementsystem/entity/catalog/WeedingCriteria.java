package com.university.librarymanagementsystem.entity.catalog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weeding_criteria")
public class WeedingCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ddc", length = 3)
    private String ddc; // Dewey Decimal Classification

    @Column(name = "min_years_old")
    private Integer minYearsOld; // Minimum number of years old for weeding consideration

    @Column(name = "condition_threshold", length = 20)
    private String conditionThreshold; // e.g., 'poor', 'fair' for physical condition

    @Column(name = "usage_threshold")
    private Integer usageThreshold; // Number of times borrowed in the last 'n' years

    @Column(name = "language", length = 50)
    private String language; // Language criteria for weeding

    @Column(name = "duplication_check")
    private Boolean duplicationCheck = true;

    @Column(name = "criteria_status")
    private Boolean criteriaStatus = true;

}
