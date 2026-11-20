package com.myclinic.backend.patient.service.rephandler;

import com.myclinic.backend.patient.entity.ConsultationSchedule;
import com.myclinic.backend.patient.repository.ConsultationScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationScheduleRepoHandler {

    private final ConsultationScheduleRepository repository;

    public ConsultationScheduleRepoHandler(ConsultationScheduleRepository repository) {
        this.repository = repository;
    }

    public ConsultationSchedule save(ConsultationSchedule schedule) {
        return repository.save(schedule);
    }

    public Optional<ConsultationSchedule> findByScheduleId(Long scheduleId) {
        return repository.findById(scheduleId);
    }

    public List<ConsultationSchedule> findConsultationByClinicIdAndDoctorId(String clinicId, Long patientId) {
        return repository.findAllByClinicIdAndDoctorIdAndStatusOrderByConsultationAtDesc(clinicId, patientId);
    }

}
