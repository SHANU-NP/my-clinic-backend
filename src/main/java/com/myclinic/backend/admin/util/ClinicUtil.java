package com.myclinic.backend.admin.util;

import com.myclinic.backend.admin.entity.ClinicUserMap;
import com.myclinic.backend.admin.servce.repoHandler.ClinicUserMapRepositoryHandler;
import org.springframework.stereotype.Component;

@Component
public class ClinicUtil {

    private final ClinicUserMapRepositoryHandler clinicUserDao;

    public ClinicUtil(ClinicUserMapRepositoryHandler clinicUserDao) {
        this.clinicUserDao = clinicUserDao;
    }

    public boolean validateUserAssociationWithClinic(String clinicId, String userId) {
        return clinicUserDao.findByClinicIdAndUserId(clinicId, userId).isPresent();
    }

    public boolean validateUserActiveInClinic(String clinicId, String userId) {
        return clinicUserDao.findByClinicIdAndUserId(clinicId, userId)
                .filter(ClinicUserMap::isActive).isPresent();
    }
}
