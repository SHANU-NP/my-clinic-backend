package com.myclinic.backend.patient.constants;

public class PatientConstants {

    public static final String MORNING = "MORNING";
    public static final String AFTERNOON = "AFTERNOON";
    public static final String EVENING = "EVENING";
    public static final String INVALID_SESSION = "Invalid session. Must be one of: MORNING, AFTERNOON, EVENING";
    public static final String PATIENT_ALREADY_IN_QUEUE = "Patient is already in the consultation queue";
    public static final String SCHEDULED = "SCHEDULED";
    public static final String CONSULTATION_DATE_REQUIRED = "Consultation date is required";

    private PatientConstants() {}


    public static final String PATIENT_NOT_FOUND = "Patient not found";
    public static final String USER_NOT_ASSOCIATED_WITH_CLINIC = "User is not associated with the clinic";
    public static final String DOB_VALIDATION_ERROR = "Invalid date of birth format. Expected format: yyyy-MM-dd";
    public static final String PATIENT = "PATIENT";
}
