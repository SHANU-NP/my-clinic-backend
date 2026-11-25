package com.myclinic.backend.patient.service.rephandler;

import com.myclinic.backend.patient.entity.ConsultationSchedule;
import com.myclinic.backend.patient.repository.ConsultationScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
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

    public Optional<ConsultationSchedule> findHighestTokenByClinicIdAndDoctorIdAndConsultationDate(
            String clinicId, String doctorId, LocalDate consultationDate) {
        return repository.findFirstByClinicIdAndDoctorIdAndConsultationDateOrderByTokenNumberDesc(
                clinicId, doctorId, consultationDate);
    }

    public List<ConsultationSchedule> findAllTokensByClinicIdAndDoctorIdAndConsultationDate(
            String clinicId, String doctorId, LocalDate consultationDate) {
        return repository.findAllByClinicIdAndDoctorIdAndConsultationDateOrderByTokenNumberDesc(
                clinicId, doctorId, consultationDate);
    }

    public Page<ConsultationSchedule> findQueuePatients(String clinicId, String doctorId, LocalDate date,
                                                          String session, Pageable pageable) {
        return repository.findAllByClinicIdAndDoctorIdAndConsultationDateAndSessionOrderByTokenNumberAsc(
                clinicId, doctorId, date, session, pageable);
    }

    public void saveConsultationSchedule(ConsultationSchedule consultationSchedule) {
        repository.save(consultationSchedule);
    }
}
