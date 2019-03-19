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

    private boolean approvedRequest; // Makes sure that nothing changes without approval
    private InstitutionAuthority institAuthority;
    private PersonAuthority personAuthority;
    private PrescriptionAuthority prescAuthority;

    private ManagementAuthority() {
        institAuthority = new InstitutionAuthority();
        personAuthority = new PersonAuthority();
        prescAuthority = new PrescriptionAuthority();
        approvedRequest = false;
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

    private void recordPerson(Person person) {
        personAuthority.record(person);
    }

    private void recordInstitution(Institution institution) {
        institAuthority.record(institution);
    }

    private void recordPrescription(Prescription prescription) {
        prescAuthority.record(prescription);
    }

    private void removePerson(Person person) {
        try {
            personAuthority.eraseRecord(person);
        } catch (RuntimeException rte) {
            // TODO (CL): proper error handling
            System.out.println(rte.getMessage());
        }
    }

    private void removeInstitution(Institution institution) {
        try {
            institAuthority.eraseRecord(institution);
        } catch (RuntimeException rte) {
            // TODO (CL): proper error handling
            System.out.println(rte.getMessage());
        }
    }

    private void removePrescription(Prescription prescription) {
        try {
            prescAuthority.eraseRecord(prescription);
        } catch (RuntimeException rte) {
            // TODO (CL): proper error handling
            System.out.println(rte.getMessage());
        }
    }

    // Requests
    private void newInstitution(Institution institution) {
        recordInstitution(institution);
    }

    private void newPerson(Person person) {
        recordPerson(person);
    }

    private void transferToInstit(Person person, Institution institution) {

    }

    public void assertApproval() throws RuntimeException {
        if (!approvedRequest) {
            throw new RuntimeException("Attempt to carry out request without approval!");
        }
    }

    public void makeRequest(RequestType requestType, Object... args) {
        // TODO (CL): implement all possible interactions here
        // TODO (CL): mutex, only one request can be processed at a time
        approvedRequest = true;
        switch (requestType) {
            case NEW_PERSON:
                break;
            case NEW_INSTITUTION:
                break;
            case PERSON_SICK:
                break;
            case PERSON_HEAL:
                break;
            case PERSON_DIE:
                break;
            case PERSON_REDEEM_PRESCRIPTION:
                break;
            case INSTITUTION_ADD_PATIENT:
                break;
            case INSTITUTION_ADD_STAFF:
                break;
            case INSTITUTION_REMOVE_STAFF:
                break;
        }

        approvedRequest = false;
    }
}
