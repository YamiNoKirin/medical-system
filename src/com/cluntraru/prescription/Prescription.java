package com.cluntraru.prescription;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.person.Civilian;
import com.cluntraru.person.Physician;

public class Prescription {
    private static int prescriptionCount = 0;
    private final int id = prescriptionCount;

    private boolean isActive;
    private String medName;
    private Physician issuedBy;
    private Civilian prescribedTo;

    public Prescription(ManagementAuthority managementAuthority, String medName, Physician issuedBy, Civilian prescribedTo) {
        managementAuthority.assertApproval();
        this.medName = medName;
        this.issuedBy = issuedBy;
        this.prescribedTo = prescribedTo;
        isActive = true;

        // TODO (CL): mutex
        ++prescriptionCount;
    }

    public String getMedicineName() {
        return medName;
    }

    public Physician getIssuedBy() {
        return issuedBy;
    }

    public Civilian getPrescribedTo() {
        return prescribedTo;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public int getId() {
        return id;
    }

    public void archive(ManagementAuthority managementAuthority) {
        managementAuthority.assertApproval();
        isActive = false;
    }
}
