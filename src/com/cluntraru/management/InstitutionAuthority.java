package com.cluntraru.management;

import com.cluntraru.institution.Hospital;
import com.cluntraru.institution.Institution;
import com.cluntraru.institution.Pharmacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InstitutionAuthority {
    // Ordered increasingly by ID
    private Map<Integer, Institution> institutions;
    private Map<Integer, Hospital> hospitals;
    private Map<Integer, Pharmacy> pharmacies;

    InstitutionAuthority() {
        institutions = new TreeMap<>();
        hospitals = new TreeMap<>();
        pharmacies = new TreeMap<>();
    }

    List<Institution> getAll() {
        return new ArrayList<>(institutions.values());
    }

    List<Hospital> getHospitals() {
        return new ArrayList<>(hospitals.values());
    }

    List<Pharmacy> getPharmacies() {
        return new ArrayList<>(pharmacies.values());
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
        else if (institution instanceof Pharmacy) {
            removePharmacy((Pharmacy) institution);
        }
        else {
            throw new RuntimeException("Erased institution " + institution.toString() + " of unsupported type.");
        }
    }

    private void updateRecord(Institution institution) {
        if (institution instanceof Hospital) {
            updateHospital((Hospital) institution);
        }
        else if (institution instanceof Pharmacy) {
            updatePharmacy((Pharmacy) institution);
        }
        else {
            throw new RuntimeException("Updated institution " + institution.toString() + " of unsupported type.");
        }
    }

    private void insertRecord(Institution institution) {
        if (institution instanceof Hospital) {
            addHospital((Hospital) institution);
        }
        else if (institution instanceof Pharmacy){
            addPharmacy((Pharmacy) institution);
        }
        else {
            throw new RuntimeException("Inserted institution " + institution.toString() + " of unsupported type.");
        }
    }

    private void addHospital(Hospital hospital) {
        institutions.put(hospital.getId(), hospital);
        hospitals.put(hospital.getId(), hospital);
    }

    private void addPharmacy(Pharmacy pharmacy) {
        institutions.put(pharmacy.getId(), pharmacy);
        pharmacies.put(pharmacy.getId(), pharmacy);
    }

    private void removeHospital(Hospital hospital) {
        institutions.remove(hospital.getId());
        hospitals.remove(hospital.getId());
    }

    private void removePharmacy(Pharmacy pharmacy) {
        institutions.remove(pharmacy.getId());
        pharmacies.remove(pharmacy.getId());
    }

    private void updateHospital(Hospital hospital) {
        removeHospital(hospital);
        addHospital(hospital);
    }

    private void updatePharmacy(Pharmacy pharmacy) {
        removePharmacy(pharmacy);
        addPharmacy(pharmacy);
    }
}
