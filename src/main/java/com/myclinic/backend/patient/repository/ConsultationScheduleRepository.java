package com.myclinic.backend.patient.repository;

import com.myclinic.backend.patient.entity.ConsultationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationScheduleRepository extends JpaRepository<ConsultationSchedule, Long> {


    List<ConsultationSchedule> findAllByClinicIdAndDoctorIdAndStatusOrderByConsultationAtDesc(String clinicId, Long patientId);
}
