package com.myclinic.backend.admin.servce.repoHandler;

import com.myclinic.backend.admin.entity.ClinicUserMap;
import com.myclinic.backend.admin.repo.ClinicUserMapRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicUserMapRepositoryHandler {

    private final ClinicUserMapRepository clinicUserMapRepository;

    public ClinicUserMapRepositoryHandler(ClinicUserMapRepository clinicUserMapRepository) {
        this.clinicUserMapRepository = clinicUserMapRepository;
    }

    public ClinicUserMap save(ClinicUserMap clinicUserMap) {
        return clinicUserMapRepository.save(clinicUserMap);
    }

    public Optional<ClinicUserMap> findById(Long id) {
        return clinicUserMapRepository.findById(id);
    }

    public List<ClinicUserMap> findByClinicId(String clinicId) {
        return clinicUserMapRepository.findByClinicId(clinicId);
    }

    public List<ClinicUserMap> findByUserId(String userId) {
        return clinicUserMapRepository.findByUserId(userId);
    }

    public Optional<ClinicUserMap> findByClinicIdAndUserId(String clinicId, String userId) {
        return clinicUserMapRepository.findByClinicIdAndUserId(clinicId, userId);
    }

    public void saveAll(List<ClinicUserMap> clinicUserMaps) {
        clinicUserMapRepository.saveAll(clinicUserMaps);
    }
}
