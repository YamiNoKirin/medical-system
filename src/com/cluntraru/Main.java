package com.cluntraru;

import com.cluntraru.institution.Hospital;
import com.cluntraru.institution.InstitutionType;
import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.management.RequestType;
import com.cluntraru.person.Person;
import com.cluntraru.person.PersonType;
import com.cluntraru.prescription.Prescription;

public class Main {

    public static void main(String[] args) {
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();

        // Create hospitals
        for (int i = 0; i < 3; ++i) {
            mgmtAuthority.makeRequest(RequestType.NEW_INSTITUTION, InstitutionType.HOSPITAL);
        }
        // Create physicians
        for (Integer i = 0; i < 10; ++i) {
            mgmtAuthority.makeRequest(RequestType.NEW_PERSON, PersonType.PHYSICIAN, "Phy" + i.toString(),
                                      mgmtAuthority.getRandomHospital());
        }

        // Create civilians
        for (Integer i = 0; i < 50; ++i) {
            mgmtAuthority.makeRequest(RequestType.NEW_PERSON, PersonType.CIVILIAN, "Civ" + i.toString());
        }

        // Issue prescriptions
        for (Integer i = 0; i < 10; ++i) {
            mgmtAuthority.makeRequest(RequestType.NEW_PRESCRIPTION, "Med" + i.toString(),
                                      mgmtAuthority.getRandomPerson());
        }

        // Archive prescriptions
        for (int i = 0; i < 3; ++i) {
            Prescription prescription = mgmtAuthority.getRandomActivePrescription();
            mgmtAuthority.makeRequest(RequestType.PERSON_REDEEM_PRESCRIPTION, prescription.getPrescribedTo(),
                                      prescription);
        }

        // Kill people
        for (int i = 0; i < 5; ++i) {
            mgmtAuthority.makeRequest(RequestType.PERSON_DIE, mgmtAuthority.getRandomPerson());
        }

        // Infect people
        for (int i = 0; i < 15; ++i) {
            mgmtAuthority.makeRequest(RequestType.PERSON_SICK, mgmtAuthority.getRandomLivePerson(),
                                      mgmtAuthority.getRandomHospital());
        }

        Person deadPerson = mgmtAuthority.getRandomPerson();
        mgmtAuthority.makeRequest(RequestType.PERSON_DIE, deadPerson);

        Person healedPerson = mgmtAuthority.getRandomPerson();
        Hospital hospital = mgmtAuthority.getRandomHospital();
        while (hospital.getStaff().isEmpty()) {
            hospital = mgmtAuthority.getRandomHospital();
        }

        mgmtAuthority.makeRequest(RequestType.PERSON_SICK, healedPerson, hospital);
        mgmtAuthority.makeRequest(RequestType.PERSON_HEAL, healedPerson);

        System.out.println("All people: " + mgmtAuthority.getPeople().toString());
        System.out.println("Live people: " + mgmtAuthority.getLivePeople().toString());
        System.out.println("Deceased people: " + mgmtAuthority.getDeceasedPeople().toString());

        System.out.println("Healthy people: " + mgmtAuthority.getHealthyPeople().toString());
        System.out.println("Sick people: " + mgmtAuthority.getSickPeople().toString());

        System.out.println("Physicians: " + mgmtAuthority.getPhysicians().toString());
        System.out.println("Civilians: " + mgmtAuthority.getCivilians().toString());

        System.out.println("Institutions: " + mgmtAuthority.getInstitutions().toString());
        System.out.println("Hospitals: " + mgmtAuthority.getHospitals().toString());

        System.out.println("Prescriptions: " + mgmtAuthority.getIssuedPrescriptions().toString());
        System.out.println("Active prescriptions: " + mgmtAuthority.getActivePrescriptions().toString());
        System.out.println("Archived prescriptions: " + mgmtAuthority.getArchivedPrescriptions().toString());

        // Change staff
        Hospital hospital1 = mgmtAuthority.getRandomHospital();
        while (hospital1 == hospital) { // Compares reference
            hospital1 = mgmtAuthority.getRandomHospital();
        }

        System.out.println("Hospital staff: " + hospital.getStaff());
        System.out.println("Other hospital staff: " + hospital1.getStaff());

        Person staffMember = hospital.getStaff().get(0);
        mgmtAuthority.makeRequest(RequestType.INSTITUTION_REMOVE_STAFF, hospital, staffMember);
        mgmtAuthority.makeRequest(RequestType.INSTITUTION_ADD_STAFF, hospital1, staffMember);

        System.out.println("After move - hospital staff: " + hospital.getStaff());
        System.out.println("After move - other hospital staff: " + hospital1.getStaff());

        System.out.println("Hospital patients: " + hospital.getPatients());
    }
}
