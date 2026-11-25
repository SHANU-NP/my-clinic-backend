package com.myclinic.backend.patient.repository;

import com.myclinic.backend.patient.entity.ConsultationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConsultationScheduleRepository extends JpaRepository<ConsultationSchedule, Long> {



    Optional<ConsultationSchedule> findFirstByClinicIdAndDoctorIdAndConsultationDateOrderByTokenNumberDesc(
            String clinicId, String doctorId, LocalDate consultationDate);

    List<ConsultationSchedule> findAllByClinicIdAndDoctorIdAndConsultationDateOrderByTokenNumberDesc(
            String clinicId, String doctorId, LocalDate consultationDate);

    Page<ConsultationSchedule> findAllByClinicIdAndDoctorIdAndConsultationDateAndSessionOrderByTokenNumberAsc(
            String clinicId, String doctorId, LocalDate consultationDate, String session, Pageable pageable);
}
