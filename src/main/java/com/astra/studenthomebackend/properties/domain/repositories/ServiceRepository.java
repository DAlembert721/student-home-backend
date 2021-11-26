package com.astra.studenthomebackend.properties.domain.repositories;


import com.astra.studenthomebackend.properties.domain.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
