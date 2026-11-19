package com.myclinic.backend.patient.repository;

import com.myclinic.backend.patient.entity.PatientDoctorConsultationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientDoctorConsultationDetailRepository
        extends JpaRepository<PatientDoctorConsultationDetail, UUID> {

    List<PatientDoctorConsultationDetail> findAllByClinicIdAndPatientIdOrderByConsultationDateDescConsultationTimeDesc(
            String clinicId, Long patientId);
}
