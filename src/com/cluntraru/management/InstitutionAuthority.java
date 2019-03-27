package com.cluntraru.management;

import com.cluntraru.institution.Hospital;
import com.cluntraru.institution.Institution;
import com.cluntraru.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InstitutionAuthority {
    // Ordered increasingly by ID
    private Map<Integer, Institution> institutions;
    private Map<Integer, Hospital> hospitals;

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

    void record(Institution institution) {
        if (institutions.containsKey(institution.getId())) {
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
        institutions.put(hospital.getId(), hospital);
        hospitals.put(hospital.getId(), hospital);
    }

    private void removeHospital(Hospital hospital) {
        institutions.remove(hospital.getId());
        hospitals.remove(hospital.getId());
    }

    private void updateHospital(Hospital hospital) {
        removeHospital(hospital);
        addHospital(hospital);
    }
}
