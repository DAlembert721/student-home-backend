package com.astra.studenthomebackend.locations.domain.repository;

import com.astra.studenthomebackend.locations.domain.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
     Page<Province> findAllProvincesByRegionId(Long regionId, Pageable pageable);
}