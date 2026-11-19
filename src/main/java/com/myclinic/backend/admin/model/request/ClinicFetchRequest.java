package com.myclinic.backend.admin.model.request;

import java.util.UUID;

import lombok.Data;

@Data
public class ClinicFetchRequest {

    private UUID clinicId;
    private String clinicName;

}
