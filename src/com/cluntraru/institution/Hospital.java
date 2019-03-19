package com.cluntraru.institution;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.person.Physician;

public class Hospital extends Institution {
    public Hospital(ManagementAuthority managementAuthority) {
        super(managementAuthority);
    }

    public void addPhysician(ManagementAuthority managementAuthority, Physician physician) {
        addStaff(managementAuthority, physician);
    }
}
