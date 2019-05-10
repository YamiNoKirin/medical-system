package com.cluntraru.service.authority;

import com.cluntraru.model.institution.Hospital;
import com.cluntraru.model.institution.Institution;
import com.cluntraru.model.institution.InstitutionType;
import com.cluntraru.model.person.Civilian;
import com.cluntraru.model.person.Person;
import com.cluntraru.model.person.PersonType;
import com.cluntraru.model.person.Physician;
import com.cluntraru.model.prescription.Prescription;
import com.cluntraru.service.logger.Logger;

import java.util.List;
import java.util.UUID;

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

    public List<Civilian> getCivilians() {
        return personAuthority.getCivilians();
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

    public List<Person> getInstitutionStaff(Institution instit) {
        return institAuthority.getInstitutionStaff(instit);
    }

    public List<Person> getInstitutionPatients(Institution instit) {
        return institAuthority.getInstitutionPatients(instit);
    }

    public Institution getInstitution(UUID uuid) {
        return institAuthority.getInstitution(uuid);
    }

    public Person getPerson(UUID uuid) {
        return personAuthority.getPerson(uuid);
    }

    public Prescription getPrescription(UUID uuid) {
        return prescAuthority.getPrescription(uuid);
    }

    // Get random
    public Hospital getRandomHospital() {
        List<Hospital> hospitals = institAuthority.getHospitals();
        int idx = idxInRange(hospitals.size());
        return hospitals.get(idx);
    }

    public Person getRandomPerson() {
        List<Person> people = personAuthority.getAll();
        int idx = idxInRange(people.size());
        return people.get(idx);
    }

    public Person getRandomHealthyPerson() {
        List<Person> people = personAuthority.getHealthy();
        int idx = idxInRange(people.size());
        return people.get(idx);
    }

    public Person getRandomLivePerson() {
        List<Person> people = personAuthority.getAlive();
        int idx = idxInRange(people.size());
        return people.get(idx);
    }

    public Prescription getRandomActivePrescription() {
        List<Prescription> prescriptions = prescAuthority.getActive();
        int idx = idxInRange(prescriptions.size());
        return prescriptions.get(idx);
    }

    private int idxInRange(int range) {
        return (int) Math.floor(Math.random() * (range - 1));
    }

    private void recordPerson(Person person) throws NullPointerException {
        if (person == null) {
            throw new NullPointerException("Person is null.");
        }

        personAuthority.record(person);
    }

    private void recordInstitution(Institution institution) throws NullPointerException {
        if (institution == null) {
            throw new NullPointerException("Institution is null.");
        }

        institAuthority.record(institution);
    }

    private void recordPrescription(Prescription prescription) throws NullPointerException {
        if (prescription == null) {
            throw new NullPointerException("Prescription is null.");
        }

        prescAuthority.record(prescription);
    }

    // Requests
    private void newInstitution(InstitutionType institutionType) {
        Institution institution;
        if (institutionType == InstitutionType.HOSPITAL) {
            institution = new Hospital();
        }
        else {
            throw new RuntimeException("Institution type " + institutionType + " cannot be created.");
        }

        recordInstitution(institution);
    }

    private void newPerson(PersonType personType, String name, Institution institution) throws RuntimeException {
        Person person;
        if (name == null) {
            throw new RuntimeException("Person cannot be created without a name.");
        }

        if (personType == PersonType.CIVILIAN) {
            if (institution == null) {
                person = new Civilian(name);
            }
            else {
                person = new Civilian(name, (Hospital) institution);
            }
        }
        else if (personType == PersonType.PHYSICIAN) {
            if (institution == null) {
                throw new RuntimeException("Physician cannot be created without a hospital.");
            }

            person = new Physician(name, (Hospital) institution);
        }
        else {
            throw new RuntimeException("Person type " + personType + " cannot be created.");
        }

        recordPerson(person);
    }

    private void newPrescription(String medName, Person prescribedTo) {
        Prescription prescription = new Prescription(medName, prescribedTo);

        recordPerson(prescribedTo);
        recordPrescription(prescription);
    }

    private void personSick(Person person, Hospital hospital) {
        if (person instanceof Civilian) {
            person.setSick(true);
            person.setInstitution(hospital);
        }
        else if (person instanceof Physician) {
            // For physicians, hospital parameter is ignored, they get treated where they work
            person.setSick(true);
        }

        recordPerson(person);
    }

    private void personHeal(Person person) {
        if (person.getInstitution() != null) {
            recordInstitution(person.getInstitution());
        }

        person.setSick(false);
        recordPerson(person);
    }

    private void personDie(Person person) {
        if (person.getInstitution() != null) {
            recordInstitution(person.getInstitution());
        }

        person.die();
        recordPerson(person);
    }

    private void personRedeemPrescription(Person person, Prescription prescription) {
        prescription.archive();
        recordPrescription(prescription);
    }

    private void institutionAddPatient(Institution institution, Person person) {
        person.setInstitution(institution);
        recordPerson(person);
    }

    private void institutionAddStaff(Institution institution, Person person) throws RuntimeException {
        if (person.getInstitution() != null) {
            throw new RuntimeException("Cannot add person that is already employed as staff.");
        }

        person.setInstitution(institution);
        recordPerson(person);
    }

    private void institutionRemoveStaff(Institution institution, Person person) {
        person.setInstitution(null);
        recordPerson(person);
    }

    public void makeRequest(RequestType requestType, Object... args) {
        Logger logger = new Logger();
        logger.logRequest(requestType);
        switch (requestType) {
            case NEW_PERSON:
                Institution institution = null;
                if (args.length >= 3) {
                    institution = (Institution) args[2];
                }

                newPerson((PersonType) args[0], (String) args[1], institution);
                break;
            case NEW_INSTITUTION:
                newInstitution((InstitutionType) args[0]);
                break;
            case NEW_PRESCRIPTION:
                newPrescription((String) args[0], (Person) args[1]);
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
    }
}
