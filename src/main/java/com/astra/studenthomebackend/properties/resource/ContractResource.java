package com.astra.studenthomebackend.properties.resource;



import com.astra.studenthomebackend.properties.domain.models.EContractStatus;
import lombok.Data;

import java.util.Date;


@Data
public class ContractResource {
    private Long id;
    private String description;
    private Float amount;
    private EContractStatus state;
    private String firstNameStudent;
    private String lastNameStudent;
    private Date createdAt;
    private Long propertyId;
    private Long studentId;
}
