package com.cluntraru.model.person;

import com.cluntraru.model.institution.Hospital;

public class Civilian extends Person {
    public Civilian(String name) {
        super(name);
    }

    public Civilian(String name, Hospital hospital) {
        super(name, hospital);
    }

    public Hospital getHospital() {
        return (Hospital) getInstitution();
    }

    public void setHospital(Hospital hospital) {
        setInstitution(hospital);
    }
}
