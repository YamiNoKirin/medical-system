package com.cluntraru.service.management;

import com.cluntraru.model.prescription.Prescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PrescriptionAuthority {
    // Key is Id
    private Map<Integer, Prescription> prescriptions;

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

    void record(Prescription prescription) {
        if (prescriptions.containsKey(prescription.getId())) {
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
        prescriptions.put(prescription.getId(), prescription);
    }

    private void removePrescription(Prescription prescription) {
        prescriptions.remove(prescription.getId());
    }

    private void updatePrescription(Prescription prescription) {
        removePrescription(prescription);
        addPrescription(prescription);
    }
}
