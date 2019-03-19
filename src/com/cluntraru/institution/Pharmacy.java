package com.cluntraru.institution;

import com.cluntraru.admin_entity.AdminEntity;
import com.cluntraru.person.Pharmacist;

public class Pharmacy extends Institution {
    public Pharmacy(AdminEntity adminEntity) {
        super(adminEntity);
    }

    public void addPharmacist(AdminEntity adminEntity, Pharmacist pharmacist) {
        addStaff(adminEntity, pharmacist);
    }
}
