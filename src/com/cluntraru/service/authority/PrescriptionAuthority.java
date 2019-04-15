package com.cluntraru.service.authority;

import com.cluntraru.model.prescription.Prescription;

import java.util.*;

public class PrescriptionAuthority {
    // Key is Id
    private Map<UUID, Prescription> prescriptions;

    PrescriptionAuthority() {
        prescriptions = new TreeMap<>();
    }

    List<Prescription> getAll() {
        return new ArrayList<>(prescriptions.values());
    }

    List<Prescription> getActive() {
        List<Prescription> activeList = new ArrayList<>();
        for (Prescription presc: prescriptions.values()) {
            if (presc.getIsActive()) {
                activeList.add(presc);
            }
        }

        return activeList;
    }

    List<Prescription> getArchived() {
        List<Prescription> archivedList = new ArrayList<>();
        for (Prescription presc: prescriptions.values()) {
            if (!presc.getIsActive()) {
                archivedList.add(presc);
            }
        }

        return archivedList;
    }

    Prescription getPrescription(UUID uuid) {
        if (uuid != null && prescriptions.containsKey(uuid)) {
            return prescriptions.get(uuid);
        }

        return null;
    }

    void record(Prescription prescription) {
        if (prescriptions.containsKey(prescription.getUUID())) {
            updateRecord(prescription);
        }
        else {
            insertRecord(prescription);
        }
    }

    void eraseRecord(Prescription prescription) {
        removePrescription(prescription);
    }

    private void insertRecord(Prescription prescription) {
        addPrescription(prescription);
    }

    private void updateRecord(Prescription prescription) {
        updatePrescription(prescription);
    }

    private void addPrescription(Prescription prescription) {
        prescriptions.put(prescription.getUUID(), prescription);
    }

    private void removePrescription(Prescription prescription) {
        prescriptions.remove(prescription.getUUID());
    }

    private void updatePrescription(Prescription prescription) {
        removePrescription(prescription);
        addPrescription(prescription);
    }
}
