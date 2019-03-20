package com.cluntraru.institution;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.person.Physician;

public class Hospital extends Institution {
    public Hospital() {
        super();
    }

    public void addPhysician(Physician physician) {
        addStaff(physician);
    }
}
