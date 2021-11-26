package com.astra.studenthomebackend.properties.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "property_images")
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String url;

    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "property_id", nullable = false)
    @JsonIgnore
    private Property property;
}
