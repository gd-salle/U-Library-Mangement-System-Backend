package com.university.librarymanagementsystem.service.catalog;

import java.util.List;

import com.university.librarymanagementsystem.dto.catalog.LocationDTO;

public interface LocationService {

    List<LocationDTO> getAllLocations();

    LocationDTO addLocation(LocationDTO locDTO);

    void deleteLocation(Integer id);

}
