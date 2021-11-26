package com.astra.studenthomebackend.locations.domain.service;


import com.astra.studenthomebackend.locations.domain.model.District;
import com.astra.studenthomebackend.locations.domain.model.Province;
import com.astra.studenthomebackend.locations.domain.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
     Page<Region> getAllRegions(Pageable pageable);
     Page<Province> getAllProvincesByRegionId(Long regionId, Pageable pageable);
     Page<District> getAllDistrictsByProvinceId(Long provinceId, Pageable pageable);
}