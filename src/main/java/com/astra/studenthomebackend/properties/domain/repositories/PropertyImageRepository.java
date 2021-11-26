package com.astra.studenthomebackend.properties.domain.repositories;


import com.astra.studenthomebackend.properties.domain.models.PropertyImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
    Page<PropertyImage> findByPropertyId(Long propertyId, Pageable pageable);
}
