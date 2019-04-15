package com.cluntraru.model.prescription;

import com.cluntraru.model.person.Person;

import java.util.UUID;

public class Prescription {
    private boolean isActive;
    private String medName;
    private Person prescribedTo;
    private UUID uuid;

    public Prescription(String medName, Person prescribedTo) {
        this.medName = medName;
        this.prescribedTo = prescribedTo;
        isActive = true;
        uuid = UUID.randomUUID();
    }

    public Prescription(String medName, Person prescribedTo, boolean isActive, UUID uuid) {
        this.medName = medName;
        this.prescribedTo = prescribedTo;
        this.isActive = isActive;
        this.uuid = uuid;
    }

    public String toString() {
        return medName;
    }

    public String getMedicineName() {
        return medName;
    }

    public Person getPrescribedTo() {
        return prescribedTo;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void archive() {
        isActive = false;
    }
}
