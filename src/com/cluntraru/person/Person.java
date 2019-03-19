package com.cluntraru.person;

import com.cluntraru.admin_entity.AdminEntity;
import com.cluntraru.institution.Institution;

public class Person {
    private static int personCount = 0;
    private final int id = personCount;

    private boolean isAlive;
    private boolean isSick;

    private String name;
    private Institution institution;

    protected Person(AdminEntity adminEntity, boolean isAlive, boolean isSick, String name) {
        // TODO (CL): record to adminEntity
        this.isAlive = isAlive;
        this.isSick = isSick;
        this.name = name;
        institution = null;

        ++personCount;
    }

    protected Person(AdminEntity adminEntity, boolean isAlive, boolean isSick, String name, Institution institution) {
        this(adminEntity, isAlive, isSick, name);
        this.institution = institution;
    }

    protected Institution getInstitution() {
        return institution;
    }

    void setInstitution(AdminEntity adminEntity, Institution institution) {
        // TODO (CL): record institution change
        this.institution = institution;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die(AdminEntity adminEntity)  {
        // TODO (CL): record
        isAlive = false;
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(AdminEntity adminEntity, boolean isSick) {
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
