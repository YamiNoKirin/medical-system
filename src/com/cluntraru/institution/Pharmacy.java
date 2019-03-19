package com.cluntraru.institution;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.person.Pharmacist;

public class Pharmacy extends Institution {
    public Pharmacy(ManagementAuthority managementAuthority) {
        super(managementAuthority);
    }

    public void addPharmacist(ManagementAuthority managementAuthority, Pharmacist pharmacist) {
        addStaff(managementAuthority, pharmacist);
    }
}
