package com.astra.studenthomebackend.properties.domain.repositories;

import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.shared.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends BaseRepository<Property, Long> {
}
