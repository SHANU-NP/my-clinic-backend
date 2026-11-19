package com.myclinic.backend.patient.service.rephandler;

import com.myclinic.backend.patient.entity.PatientDoctorConsultationDetail;
import com.myclinic.backend.patient.repository.PatientDoctorConsultationDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientDoctorConsultationDetailRepoHandler {
    private final PatientDoctorConsultationDetailRepository repository;

    public PatientDoctorConsultationDetailRepoHandler(PatientDoctorConsultationDetailRepository repository) {
        this.repository = repository;
    }

    public PatientDoctorConsultationDetail save(PatientDoctorConsultationDetail consultation) {
        return repository.save(consultation);
    }

    public Optional<PatientDoctorConsultationDetail> findByConsultationId(UUID consultationId) {
        return repository.findById(consultationId);
    }

    public List<PatientDoctorConsultationDetail> findByClinicAndPatient(String clinicId, Long patientId) {
        return repository.findAllByClinicIdAndPatientIdOrderByConsultationDateDescConsultationTimeDesc(clinicId, patientId);
    }
}
