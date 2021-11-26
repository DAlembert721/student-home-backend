package com.astra.studenthomebackend.properties.domain.models;

import com.astra.studenthomebackend.accounts.domain.model.LandLord;
import com.astra.studenthomebackend.locations.domain.model.District;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "property_services",
            joinColumns = { @JoinColumn(name = "property_id")},
            inverseJoinColumns = { @JoinColumn(name = "service_id")})
    private List<Service> services;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    private District district;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "land_lord_id", nullable = false)
    @JsonIgnore
    private LandLord landLord;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<PropertyImage> propertyImages;

    public boolean haveService(Service service) {
        return this.getServices().contains(service);
    }

    public Property addService(Service service) {
        if(!this.haveService(service))
            this.getServices().add(service);
        return this;
    }

    public Property removeService(Service service) {
        if(this.haveService(service))
            this.getServices().remove(service);
        return this;
    }
}
