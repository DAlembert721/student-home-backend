package com.astra.studenthomebackend.accounts.resource;


import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class StudentResource extends AccountResource {

    private String address;

    private String image;

    private String districtName;

    private String educationCenterName;

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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getEducationCenterName() {
        return educationCenterName;
    }

    public void setEducationCenterName(String educationCenterName) {
        this.educationCenterName = educationCenterName;
    }
}
