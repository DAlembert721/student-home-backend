package com.astra.studenthomebackend.accounts.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveLandLordResource extends SaveAccountResource {

    @NotNull
    private Long subscriptionId;
}
