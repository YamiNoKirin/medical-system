package com.cluntraru.service.authority;

public enum RequestType {
    NEW_PERSON,
    NEW_INSTITUTION,
    NEW_PRESCRIPTION,
    PERSON_SICK, // Person gets added to hospital and pharmacy as patient
    PERSON_HEAL, // Person gets removed from all institutions
    PERSON_DIE, // Person gets removed from all institutions
    PERSON_REDEEM_PRESCRIPTION, // Prescription gets archived
    INSTITUTION_ADD_STAFF, // Person gets removed from previous staff positions and added to new one
    INSTITUTION_ADD_PATIENT, // Person gets added to institution as patient
    INSTITUTION_REMOVE_STAFF, // Person gets removed from institution as staff
}
