package com.myclinic.backend.doctor.model.response;

import java.util.List;

public record DoctorResponse(
        List<DoctorInfo>  doctors
) {
}
