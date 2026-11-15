package com.myclinic.backend.admin.model.request;

import lombok.Data;

@Data
public class ClinicFetchRequest {

    private Long clinicId;
    private String clinicName;

}
