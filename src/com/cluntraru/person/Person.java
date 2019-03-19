package com.cluntraru.person;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.institution.Institution;

abstract public class Person {
    private static int personCount = 0;
    private final int id = personCount;

    private boolean isAlive;
    private boolean isSick;

    private String name;
    private Institution institution;

    // Class specific
    protected Person(ManagementAuthority managementAuthority, String name) {
        managementAuthority.assertApproval();
        isAlive = true;
        isSick = false;
        this.name = name;
        institution = null;

        // TODO (CL): mutex
        ++personCount;
    }

    protected Person(ManagementAuthority managementAuthority, String name, Institution institution) {
        this(managementAuthority, name);
        this.institution = institution;
    }

    public Institution getInstitution() {
        return institution;
    }

    // Other methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isSick() {
        return isSick;
    }

    public void die(ManagementAuthority managementAuthority)  {
        managementAuthority.assertApproval();
        isAlive = false;
    }

    public void setSick(ManagementAuthority managementAuthority, boolean isSick) {
        managementAuthority.assertApproval();
        this.isSick = isSick;
    }

    public void setInstitution(ManagementAuthority managementAuthority, Institution institution) {
        managementAuthority.assertApproval();
        this.institution = institution;
    }
}
