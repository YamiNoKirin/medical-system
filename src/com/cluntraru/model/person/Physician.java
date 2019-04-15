package com.cluntraru.model.person;

import com.cluntraru.model.institution.Hospital;

import java.util.UUID;

public class Physician extends Person {
    public Physician(String name) {
        super(name);
    }

    public Physician(String name, Hospital hospital) {
        super(name, hospital);
    }

    public Physician(String name, Hospital hospital, boolean isAlive, boolean isSick, UUID uuid) {
        super(name, hospital, isAlive, isSick, uuid);
    }

    public Hospital getHospital() {
        return (Hospital) getInstitution();
    }

    public void setHospital(Hospital hospital) {
        setInstitution(hospital);
    }
}
