package com.cluntraru.person;

import com.cluntraru.institution.Institution;

abstract public class Person {
    private static int personCount = 0;
    private final int id = personCount;

    private boolean isAlive;
    private boolean isSick;

    private String name;
    private Institution institution;

    // Class specific
    protected Person(String name) {
        isAlive = true;
        isSick = false;
        this.name = name;
        institution = null;

        // TODO (CL): mutex
        ++personCount;
    }

    protected Person(String name, Institution institution) {
        this(name);
        this.institution = institution;
    }

    public Institution getInstitution() {
        return institution;
    }

    public String toString() {
        return name;
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
