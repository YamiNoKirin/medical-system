package com.cluntraru.model.institution;

import com.cluntraru.model.person.Person;

import java.util.*;

abstract public class Institution {
    private Map<UUID, Person> staff;
    private Map<UUID, Person> patients;
    private UUID uuid;

    protected Institution() {
        staff = new TreeMap<>();
        patients = new TreeMap<>();
        uuid = UUID.randomUUID();
    }

    protected Institution(UUID uuid) {
        staff = new TreeMap<>();
        patients = new TreeMap<>();
        this.uuid = uuid;
    }

    public List<Person> getStaff() {
        return new ArrayList<>(staff.values());
    }

    public List<Person> getPatients() {
        return new ArrayList<>(patients.values());
    }

    public UUID getUUID() {
        return uuid;
    }

    public void addStaff(Person person) {
        staff.put(person.getUUID(), person);
    }

    public void removeStaff(Person person) {
        staff.remove(person.getUUID());
    }

    public void addPatient(Person person) {
        patients.put(person.getUUID(), person);
    }

    public void removePatient(Person person) {
        patients.remove(person.getUUID(), person);
    }
}
