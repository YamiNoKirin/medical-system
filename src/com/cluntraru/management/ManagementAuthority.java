package com.cluntraru.management;

import com.cluntraru.institution.Hospital;
import com.cluntraru.institution.Institution;
import com.cluntraru.institution.Pharmacy;
import com.cluntraru.person.Person;
import com.cluntraru.person.Pharmacist;
import com.cluntraru.person.Physician;
import com.cluntraru.prescription.Prescription;

import java.util.ArrayList;
import java.util.List;

public final class ManagementAuthority {
    private static ManagementAuthority instance;

    private InstitutionAuthority institAuthority;
    private PersonAuthority personAuthority;
    private PrescriptionAuthority prescAuthority;

    private ManagementAuthority() {
        institAuthority = new InstitutionAuthority();
        personAuthority = new PersonAuthority();
        prescAuthority = new PrescriptionAuthority();
    }

    public static ManagementAuthority getInstance() {
        if (instance == null) {
            instance = new ManagementAuthority();
        }

        return instance;
    }

    public List<Person> getPeople() {
        return personAuthority.getAll();
    }

    public List<Person> getLivePeople() {
        return personAuthority.getAlive();
    }

    public List<Person> getDeceasedPeople() {
        return personAuthority.getDeceased();
    }

    public List<Person> getSickPeople() {
        return personAuthority.getSick();
    }

    public List<Person> getHealthyPeople() {
        return personAuthority.getHealthy();
    }

    public List<Physician> getPhysicians() {
        return personAuthority.getPhysicians();
    }

    public List<Pharmacist> getPharmacistList() {
        return personAuthority.getPharmacists();
    }

    public List<Prescription> getIssuedPrescriptions() {
        return prescAuthority.getAll();
    }

    public List<Prescription> getActivePrescriptions() {
        return prescAuthority.getActive();
    }

    public List<Prescription> getArchivedPrescriptions() {
        return prescAuthority.getArchived();
    }

    public List<Institution> getInstitutions() {
        return institAuthority.getAll();
    }

    public List<Hospital> getHospitals() {
        return institAuthority.getHospitals();
    }

    public List<Pharmacy> getPharmacies() {
        return institAuthority.getPharmacies();
    }

    public void recordPerson(Person person) {
        personAuthority.record(person);
    }

    public void recordInstitution(Institution institution) {
        institAuthority.record(institution);
    }

    public void recordPrescription(Prescription prescription) {
        prescAuthority.record(prescription);
    }

    public void removePerson(Person person) {
        try {
            personAuthority.eraseRecord(person);
        } catch (RuntimeException rte) {
            // TODO (CL): proper error handling
            System.out.println(rte.getMessage());
        }
    }

    public void removeInstitution(Institution institution) {
        try {
            institAuthority.eraseRecord(institution);
        } catch (RuntimeException rte) {
            // TODO (CL): proper error handling
            System.out.println(rte.getMessage());
        }
    }

    public void removePrescription(Prescription prescription) {
        try {
            prescAuthority.eraseRecord(prescription);
        } catch (RuntimeException rte) {
            // TODO (CL): proper error handling
            System.out.println(rte.getMessage());
        }
    }
}
