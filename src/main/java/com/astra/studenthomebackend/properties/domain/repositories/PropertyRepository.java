package com.astra.studenthomebackend.properties.domain.repositories;

import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.shared.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends BaseRepository<Property, Long> {
    Page<Property> findByLandLordId(Long landLordId, Pageable pageable);
    Page<Property> findByActive(Boolean active, Pageable pageable);
}
