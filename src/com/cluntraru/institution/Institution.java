package com.cluntraru.institution;

import com.cluntraru.person.Person;

import java.util.*;

abstract public class Institution {
    private static int institutionCount = 0;
    private final int id = institutionCount;

    private Map<Integer, Person> staff;
    private Map<Integer, Person> patients;

    Institution() {
        staff = new TreeMap<>();
        patients = new TreeMap<>();

        // TODO (CL): mutex
        ++institutionCount;
    }

    public List<Person> getStaff() {
        return new ArrayList<>(staff.values());
    }

    public List<Person> getPatients() {
        return new ArrayList<>(patients.values());
    }

    public int getId() {
        return id;
    }

    public void addStaff(Person person) {
        staff.put(person.getId(), person);
    }

    public void removeStaff(Person person) {
        staff.remove(person.getId());
    }

    public void addPatient(Person person) {
        patients.put(person.getId(), person);
    }

    public void removePatient(Person person) {
        patients.remove(person.getId(), person);
    }
}
