package com.myclinic.backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicResponse {

    private String clinicId;
    private String clinicName;

}
