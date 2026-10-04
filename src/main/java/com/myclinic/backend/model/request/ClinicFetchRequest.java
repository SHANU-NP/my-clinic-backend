package com.myclinic.backend.model.request;

import lombok.Data;

@Data
public class ClinicFetchRequest {

    private Long clinicId;
    private String clinicName;

}
