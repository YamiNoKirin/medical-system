package com.cluntraru.institution;

import com.cluntraru.admin_entity.AdminEntity;
import com.cluntraru.person.Physician;

public class Hospital extends Institution {
    public Hospital(AdminEntity adminEntity) {
        super(adminEntity);
    }

    public void addPhysician(AdminEntity adminEntity, Physician physician) {
        addStaff(adminEntity, physician);
    }
}
