package com.astra.studenthomebackend.properties.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "properties")
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long rooms;

    @NotNull
    private Float size;

    @NotNull
    private Float cost;

    @NotNull
    private Boolean active;

    @NotNull
    @Size(max = 100)
    private String address;

    @Size(max = 100)
    private String title;

    @Size(max = 400)
    private String description;
}
