package com.cluntraru.person;

import com.cluntraru.admin_entity.AdminEntity;
import com.cluntraru.institution.Pharmacy;

public class Pharmacist extends Person {
    public Pharmacist(AdminEntity adminEntity, boolean isAlive, boolean isSick, String name) {
        super(adminEntity, isAlive, isSick, name);
    }

    public Pharmacist(AdminEntity adminEntity, boolean isAlive, boolean isSick, String name, Pharmacy pharmacy) {
        super(adminEntity, isAlive, isSick, name, pharmacy);
    }

    public Pharmacy getPharmacy() {
        return (Pharmacy) getInstitution();
    }

    public void setPharmacy(AdminEntity adminEntity, Pharmacy pharmacy) {
        setInstitution(adminEntity, pharmacy);
    }
}
