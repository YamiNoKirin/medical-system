package com.cluntraru.model.prescription;

import com.cluntraru.model.person.Person;

public class Prescription {
    private static int prescriptionCount = 0;
    private final int id = prescriptionCount;

    private boolean isActive;
    private String medName;
    private Person prescribedTo;

    public Prescription(String medName, Person prescribedTo) {
        this.medName = medName;
        this.prescribedTo = prescribedTo;
        isActive = true;

        // TODO (CL): mutex
        ++prescriptionCount;
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

    public int getId() {
        return id;
    }

    public void archive() {
        isActive = false;
    }
}
