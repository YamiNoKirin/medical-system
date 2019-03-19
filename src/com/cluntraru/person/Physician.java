package com.cluntraru.person;

import com.cluntraru.admin_entity.AdminEntity;
import com.cluntraru.institution.Hospital;

public class Physician extends Person {
    public Physician(AdminEntity adminEntity, boolean isAlive, boolean isSick, String name) {
        super(adminEntity, isAlive, isSick, name);
    }

    public Physician(AdminEntity adminEntity, boolean isAlive, boolean isSick, String name, Hospital hospital) {
        super(adminEntity, isAlive, isSick, name, hospital);
    }

    public Hospital getHospital() {
        return (Hospital) getInstitution();
    }

    public void setHospital(AdminEntity adminEntity, Hospital hospital) {
        setInstitution(adminEntity, hospital);
    }
}
