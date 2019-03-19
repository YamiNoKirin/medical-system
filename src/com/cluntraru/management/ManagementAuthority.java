package com.cluntraru.management;

import com.cluntraru.institution.Hospital;
import com.cluntraru.institution.Institution;
import com.cluntraru.institution.InstitutionType;
import com.cluntraru.person.*;
import com.cluntraru.prescription.Prescription;

import java.util.ArrayList;
import java.util.List;

public final class ManagementAuthority {
    private static ManagementAuthority instance;

    // TODO (CL): if a thread is created that directly calls a method which needs approval, while one is called by
    // the management authority, it can be circumvented (if they are called simultaneously)
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
            System.out.println(rte.getMessage());
        }
    }

    private void removeInstitution(Institution institution) {
        try {
            institAuthority.eraseRecord(institution);
        } catch (RuntimeException rte) {
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
    private Institution newInstitution(InstitutionType institutionType) {
        Institution institution;
        if (institutionType == InstitutionType.HOSPITAL) {
            institution = new Hospital(this);
        }
        else {
            throw new RuntimeException("Institution type " + institutionType + " cannot be created.");
        }

        recordInstitution(institution);
        return institution;
    }

    private Person newPerson(PersonType personType, String name) {
        Person person;
        if (personType == PersonType.CIVILIAN) {
            person = new Civilian(this, name);
        }
        else if (personType == PersonType.PHYSICIAN) {
            person = new Physician(this, name);
        }
        else {
            throw new RuntimeException("Institution type " + personType + " cannot be created.");
        }

        recordPerson(person);
        return person;
    }

    private Prescription newPrescription(String medName, Person prescribedTo) {
        Prescription prescription = new Prescription(this, medName, prescribedTo);

        recordPerson(prescribedTo);
        recordPrescription(prescription);
        return prescription;
    }

    private void personSick(Person person, Hospital hospital) {
        if (person instanceof Civilian) {
            person.setSick(this, true);
            person.setInstitution(this, hospital);
            hospital.addPatient(this, person);
        }
        else if (person instanceof Physician) {
            // For physicians, hospital parameter is ignored, they get treated where they work
            person.setSick(this, true);
            hospital.addPatient(this, person);
        }

        recordInstitution(hospital);
        recordPerson(person);
    }

    private void personHeal(Person person) {
        person.setSick(this, false);
        person.getInstitution().removePatient(this, person);

        recordInstitution(person.getInstitution());
        recordPerson(person);
    }

    private void personDie(Person person) {
        person.die(this);
        person.getInstitution().removePatient(this, person);

        recordInstitution(person.getInstitution());
        recordPerson(person);
    }

    private void personRedeemPrescription(Person person, Prescription prescription) {
        prescription.archive(this);
        recordPrescription(prescription);
    }

    private void institutionAddPatient(Institution institution, Person person) {
        institution.addPatient(this, person);
        person.setInstitution(this, institution);

        recordInstitution(institution);
        recordPerson(person);
    }

    private void institutionAddStaff(Institution institution, Person person) {
        institution.addStaff(this, person);
        person.setInstitution(this, institution);

        recordInstitution(institution);
        recordPerson(person);
    }

    private void institutionRemoveStaff(Institution institution, Person person) {
        institution.removeStaff(this, person);
        person.setInstitution(this, null);

        recordInstitution(institution);
        recordPerson(person);
    }

    public void assertApproval() throws RuntimeException {
        if (!approvedRequest) {
            throw new RuntimeException("Attempt to carry out request without approval!");
        }
    }

    public Object makeRequest(RequestType requestType, Object... args) {
        // TODO (CL): mutex, only one request can be processed at a time (until migrated to persistent storage)
        approvedRequest = true;
        Object retVal = null;
        switch (requestType) {
            case NEW_PERSON:
                retVal = newPerson((PersonType) args[0], (String) args[1]);
                break;
            case NEW_INSTITUTION:
                retVal = newInstitution((InstitutionType) args[0]);
                break;
            case NEW_PRESCRIPTION:
                retVal = newPrescription((String) args[0], (Person) args[1]);
                break;
            case PERSON_SICK:
                personSick((Person) args[0], (Hospital) args[1]);
                break;
            case PERSON_HEAL:
                personHeal((Person) args[0]);
                break;
            case PERSON_DIE:
                personDie((Person) args[0]);
                break;
            case PERSON_REDEEM_PRESCRIPTION:
                personRedeemPrescription((Person) args[0], (Prescription) args[1]);
                break;
            case INSTITUTION_ADD_PATIENT:
                institutionAddPatient((Institution) args[0], (Person) args[1]);
                break;
            case INSTITUTION_ADD_STAFF:
                institutionAddStaff((Institution) args[0], (Person) args[1]);
                break;
            case INSTITUTION_REMOVE_STAFF:
                institutionRemoveStaff((Institution) args[0], (Person) args[1]);
                break;
        }

        approvedRequest = false;
        return retVal;
    }
}
