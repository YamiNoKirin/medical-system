package com.cluntraru.model.person;

import com.cluntraru.model.institution.Institution;

import java.util.UUID;

abstract public class Person {
    private boolean isAlive;
    private boolean isSick;

    private String name;
    private Institution institution;
    private UUID uuid;

    // Class specific
    protected Person(String name) {
        isAlive = true;
        isSick = false;
        this.name = name;
        institution = null;
        uuid = UUID.randomUUID();
    }

    protected Person(String name, Institution institution) {
        this(name);
        this.institution = institution;
    }

    protected Person(String name, Institution institution, boolean isAlive, boolean isSick, UUID uuid) {
        this.name = name;
        this.institution = institution;
        this.isAlive = isAlive;
        this.isSick = isSick;
        this.uuid = uuid;
    }

    public Institution getInstitution() {
        return institution;
    }

    public String toString() {
        return name;
    }

    // Other methods
    public UUID getUUID() {
        return uuid;
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

    public void die()  {
        isAlive = false;
    }

    public void setSick(boolean isSick) {
        this.isSick = isSick;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
