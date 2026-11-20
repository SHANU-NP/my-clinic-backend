package com.myclinic.backend.doctor.model.response;


public record DoctorProfileResponse(
        String doctorId,
        String name,
        String contactNumber,
        String designation,
        String specialization,
        String clinicId,
        String clinicName
) {
}
