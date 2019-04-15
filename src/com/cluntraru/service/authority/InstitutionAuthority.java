package com.cluntraru.service.authority;

import com.cluntraru.model.institution.Hospital;
import com.cluntraru.model.institution.Institution;

import java.util.*;

public class InstitutionAuthority {
    // Ordered increasingly by ID
    private Map<UUID, Institution> institutions;
    private Map<UUID, Hospital> hospitals;

    InstitutionAuthority() {
        institutions = new TreeMap<>();
        hospitals = new TreeMap<>();
    }

    List<Institution> getAll() {
        return new ArrayList<>(institutions.values());
    }

    List<Hospital> getHospitals() {
        return new ArrayList<>(hospitals.values());
    }

    Institution getInstitution(UUID uuid) {
        if (uuid != null && institutions.containsKey(uuid)) {
            return institutions.get(uuid);
        }

        return null;
    }

    void record(Institution institution) {
        if (institutions.containsKey(institution.getUUID())) {
            updateRecord(institution);
        }
        else {
            insertRecord(institution);
        }
    }

    void eraseRecord(Institution institution) {
        if (institution instanceof Hospital) {
            removeHospital((Hospital) institution);
        }
        else {
            throw new RuntimeException("Erased institution " + institution.toString() + " of unsupported type.");
        }
    }

    private void updateRecord(Institution institution) {
        if (institution instanceof Hospital) {
            updateHospital((Hospital) institution);
        }
        else {
            throw new RuntimeException("Updated institution " + institution.toString() + " of unsupported type.");
        }
    }

    private void insertRecord(Institution institution) {
        if (institution instanceof Hospital) {
            addHospital((Hospital) institution);
        }
        else {
            throw new RuntimeException("Inserted institution " + institution.toString() + " of unsupported type.");
        }
    }

    private void addHospital(Hospital hospital) {
        institutions.put(hospital.getUUID(), hospital);
        hospitals.put(hospital.getUUID(), hospital);
    }

    private void removeHospital(Hospital hospital) {
        institutions.remove(hospital.getUUID());
        hospitals.remove(hospital.getUUID());
    }

    private void updateHospital(Hospital hospital) {
        removeHospital(hospital);
        addHospital(hospital);
    }
}
