package com.astra.studenthomebackend.locations.domain.repository;

import com.astra.studenthomebackend.locations.domain.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Long> {

}
