package com.cluntraru.person;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.institution.Pharmacy;

public class Pharmacist extends Person {
    public Pharmacist(ManagementAuthority managementAuthority, boolean isAlive, boolean isSick, String name) {
        super(managementAuthority, isAlive, isSick, name);
    }

    public Pharmacist(ManagementAuthority managementAuthority, boolean isAlive, boolean isSick, String name, Pharmacy pharmacy) {
        super(managementAuthority, isAlive, isSick, name, pharmacy);
    }

    public Pharmacy getPharmacy() {
        return (Pharmacy) getInstitution();
    }

    public void setPharmacy(ManagementAuthority managementAuthority, Pharmacy pharmacy) {
        setInstitution(managementAuthority, pharmacy);
    }
}
