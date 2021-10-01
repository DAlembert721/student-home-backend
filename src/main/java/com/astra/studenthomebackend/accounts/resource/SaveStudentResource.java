package com.astra.studenthomebackend.accounts.resource;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveStudentResource extends AccountResource {

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    @Size(max = 250)
    private String image;

    @NotNull
    private Long districtId;

    @NotNull
    private Long educationCenterId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getEducationCenterId() {
        return educationCenterId;
    }

    public void setEducationCenterId(Long educationCenterId) {
        this.educationCenterId = educationCenterId;
    }
}
