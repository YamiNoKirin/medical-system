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

    protected Person(ManagementAuthority managementAuthority, boolean isAlive, boolean isSick, String name) {
        // TODO (CL): record to managementAuthority
        this.isAlive = isAlive;
        this.isSick = isSick;
        this.name = name;
        institution = null;

        ++personCount;
    }

    protected Person(ManagementAuthority managementAuthority, boolean isAlive, boolean isSick, String name, Institution institution) {
        this(managementAuthority, isAlive, isSick, name);
        this.institution = institution;
    }

    protected Institution getInstitution() {
        return institution;
    }

    void setInstitution(ManagementAuthority managementAuthority, Institution institution) {
        // TODO (CL): record institution change
        this.institution = institution;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die(ManagementAuthority managementAuthority)  {
        // TODO (CL): record
        isAlive = false;
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(ManagementAuthority managementAuthority, boolean isSick) {
        // TODO (CL): record
        this.isSick = isSick;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
