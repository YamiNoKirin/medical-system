package com.cluntraru.model.person;

import com.cluntraru.model.institution.Hospital;

import java.util.UUID;

public class Civilian extends Person {
    public Civilian(String name) {
        super(name);
    }

    public Civilian(String name, Hospital hospital) {
        super(name, hospital);
    }

    public Civilian(String name, Hospital hospital, boolean isAlive, boolean isSick, UUID uuid) {
        super(name, hospital, isAlive, isSick, uuid);
    }

    public Hospital getHospital() {
        return (Hospital) getInstitution();
    }

    public void setHospital(Hospital hospital) {
        setInstitution(hospital);
    }
}
