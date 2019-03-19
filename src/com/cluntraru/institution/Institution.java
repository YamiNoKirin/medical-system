package com.cluntraru.institution;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.person.Person;

import java.util.*;

abstract public class Institution {
    private static int institutionCount = 0;
    private final int id = institutionCount;

    private Map<Integer, Person> staff;
    private Map<Integer, Person> patients;

    Institution(ManagementAuthority managementAuthority) {
        managementAuthority.assertApproval();
        staff = new TreeMap<>();

        // TODO (CL): mutex
        ++institutionCount;
    }

    public List<Person> getStaff() {
        return new ArrayList<>(staff.values());
    }

    public List<Person> getPatients() {
        return new ArrayList<>(staff.values());
    }

    public int getId() {
        return id;
    }

    public void addStaff(ManagementAuthority managementAuthority, Person person) {
        managementAuthority.assertApproval();
        staff.put(person.getId(), person);
    }

    public void removeStaff(ManagementAuthority managementAuthority, Person person) {
        managementAuthority.assertApproval();
        staff.remove(person.getId());
    }

    public void addPatient(ManagementAuthority managementAuthority, Person person) {
        managementAuthority.assertApproval();
        patients.put(person.getId(), person);
    }

    public void removePatient(ManagementAuthority managementAuthority, Person person) {
        managementAuthority.assertApproval();
        patients.remove(person.getId(), person);
    }
}
