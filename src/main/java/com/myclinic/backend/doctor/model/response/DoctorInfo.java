package com.myclinic.backend.doctor.model.response;

public record DoctorInfo(
    String doctorId,
    String doctorName,
    String designation,
    String specialization
) {
}
