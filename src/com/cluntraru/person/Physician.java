package com.cluntraru.person;

import com.cluntraru.institution.Hospital;

public class Physician extends Person {
    public Physician(String name) {
        super(name);
    }

    public Physician(String name, Hospital hospital) {
        super(name, hospital);
    }

    public Hospital getHospital() {
        return (Hospital) getInstitution();
    }

    public void setHospital(Hospital hospital) {
        setInstitution(hospital);
    }
}
