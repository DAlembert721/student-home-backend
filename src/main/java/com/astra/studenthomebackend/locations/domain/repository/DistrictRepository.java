package com.astra.studenthomebackend.locations.domain.repository;

import com.astra.studenthomebackend.locations.domain.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
     Page<District> findAllDistrictsByProvinceId(Long provinceId, Pageable pageable);
}