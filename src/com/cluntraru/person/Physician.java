package com.cluntraru.person;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.institution.Hospital;

public class Physician extends Person {
    public Physician(ManagementAuthority managementAuthority, String name) {
        super(managementAuthority, name);
    }

    public Physician(ManagementAuthority managementAuthority, String name, Hospital hospital) {
        super(managementAuthority, name, hospital);
    }

    public Hospital getHospital() {
        return (Hospital) getInstitution();
    }

    public void setHospital(ManagementAuthority managementAuthority, Hospital hospital) {
        setInstitution(managementAuthority, hospital);
    }
}
