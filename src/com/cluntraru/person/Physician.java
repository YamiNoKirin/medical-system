package com.cluntraru.person;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.institution.Hospital;

public class Physician extends Person {
    public Physician(ManagementAuthority managementAuthority, boolean isAlive, boolean isSick, String name) {
        super(managementAuthority, isAlive, isSick, name);
    }

    public Physician(ManagementAuthority managementAuthority, boolean isAlive, boolean isSick, String name, Hospital hospital) {
        super(managementAuthority, isAlive, isSick, name, hospital);
    }

    public Hospital getHospital() {
        return (Hospital) getInstitution();
    }

    public void setHospital(ManagementAuthority managementAuthority, Hospital hospital) {
        setInstitution(managementAuthority, hospital);
    }
}
