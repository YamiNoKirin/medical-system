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
    protected Person(ManagementAuthority managementAuthority, boolean isAlive, boolean isSick, String name) {
        managementAuthority.assertApproval();
        this.isAlive = isAlive;
        this.isSick = isSick;
        this.name = name;
        institution = null;

        // TODO (CL): mutex
        ++personCount;
    }

    protected Person(ManagementAuthority managementAuthority, boolean isAlive, boolean isSick, String name, Institution institution) {
        this(managementAuthority, isAlive, isSick, name);
        this.institution = institution;
    }

    protected Institution getInstitution() {
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
