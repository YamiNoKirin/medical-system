package com.cluntraru.admin_entity;

import com.cluntraru.institution.Hospital;
import com.cluntraru.institution.Institution;
import com.cluntraru.institution.Pharmacy;
import com.cluntraru.person.Person;
import com.cluntraru.person.Pharmacist;
import com.cluntraru.person.Physician;
import com.cluntraru.prescription.Prescription;

import java.util.ArrayList;
import java.util.List;

public final class AdminEntity {
    private static AdminEntity instance;

    // All of these are sorted increasingly by id
    private List<Person> personList;
    private List<Physician> physicianList;
    private List<Pharmacist> pharmacistList;
    private List<Prescription> issuedPrescriptionList;
    private List<Institution> institutionList;

    private AdminEntity() {
        personList = new ArrayList<>();
        physicianList = new ArrayList<>();
        pharmacistList = new ArrayList<>();
        issuedPrescriptionList = new ArrayList<>();
        institutionList = new ArrayList<>();
    }

    public static AdminEntity getInstance() {
        if (instance == null) {
            instance = new AdminEntity();
        }

        return instance;
    }

    public List<Person> getPeople() {
        return personList;
    }

    public List<Person> getLivePeople() {
        // TODO (CL): get live from people
        return null;
    }

    public List<Person> getDeceasedPeople() {
        // TODO (CL): get deceased from people
        return null;
    }

    public List<Person> getSickPeople() {
        // TODO (CL): get sick from people
        return null;
    }

    public List<Physician> getPhysicians() {
        return physicianList;
    }

    public List<Pharmacist> getPharmacistList() {
        return pharmacistList;
    }

    public List<Prescription> getIssuedPrescriptions() {
        return issuedPrescriptionList;
    }

    public List<Prescription> getActivePrescriptions() {
        // TODO (CL): get active prescriptions from issued
        return null;
    }

    public List<Prescription> getArchivedPrescriptions() {
        // TODO (CL): get archived prescriptions from issued
        return null;
    }

    public List<Institution> getInstitutions() {
        return institutionList;
    }

    public List<Hospital> getHospitals() {
        // TODO (CL): get hospitals from institutions
        return null;
    }

    public List<Pharmacy> getPharmacies() {
        // TODO (CL): get pharmacies from institutions
        return null;
    }
}
