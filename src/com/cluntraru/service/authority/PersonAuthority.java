package com.cluntraru.service.authority;

import com.cluntraru.model.person.Civilian;
import com.cluntraru.model.person.Person;
import com.cluntraru.model.person.Physician;

import java.util.*;

public class PersonAuthority {
    // Key is Id
    private Map<UUID, Person> people;
    private Map<UUID, Civilian> civilians;
    private Map<UUID, Physician> physicians;

    PersonAuthority() {
        people = new TreeMap<>();
        civilians = new TreeMap<>();
        physicians = new TreeMap<>();
    }

    List<Person> getAll() {
        return new ArrayList<>(people.values());
    }

    List<Person> getAlive() {
        List<Person> aliveList = new ArrayList<>();
        for (Person person: people.values()) {
            if (person.isAlive()) {
                aliveList.add(person);
            }
        }

        return aliveList;
    }

    List<Person> getDeceased() {
        List<Person> deceasedList = new ArrayList<>();
        for (Person person: people.values()) {
            if (!person.isAlive()) {
                deceasedList.add(person);
            }
        }

        return deceasedList;
    }

    List<Person> getSick() {
        List<Person> sickList = new ArrayList<>();
        for (Person person: people.values()) {
            if (person.isAlive() && person.isSick()) {
                sickList.add(person);
            }
        }

        return sickList;
    }

    List<Person> getHealthy() {
        List<Person> healthyList = new ArrayList<>();
        for (Person person: people.values()) {
            if (person.isAlive() && !person.isSick()) {
                healthyList.add(person);
            }
        }

        return healthyList;
    }

    Person getPerson(UUID uuid) {
        if (uuid != null && people.containsKey(uuid)) {
            return people.get(uuid);
        }

        return null;
    }

    List<Civilian> getCivilians() {
        return new ArrayList<>(civilians.values());
    }

    List<Physician> getPhysicians() {
        return new ArrayList<>(physicians.values());
    }

    void record(Person person) {
        if (people.containsKey(person.getUUID())) {
            updateRecord(person);
        }
        else {
            insertRecord(person);
        }
    }

    void eraseRecord(Person person) {
        if (person instanceof Civilian) {
            removeCivilian((Civilian) person);
        }
        else if (person instanceof Physician) {
            removePhysician((Physician) person);
        }
        else {
            throw new RuntimeException("Erased person " + person.toString() + " of unsupported type.");
        }
    }

    private void insertRecord(Person person) throws  RuntimeException {
        if (person instanceof Civilian) {
            addCivilian((Civilian) person);
        }
        else if (person instanceof Physician) {
            addPhysician((Physician) person);
        }
        else {
            throw new RuntimeException("Inserted person " + person.toString() + " of unsupported type.");
        }
    }

    private void updateRecord(Person person) throws RuntimeException {
        if (person instanceof Civilian) {
            updateCivilian((Civilian) person);
        }
        else if (person instanceof Physician) {
            updatePhysician((Physician) person);
        }
        else {
            throw new RuntimeException("Updated person " + person.toString() + " of unsupported type.");
        }
    }

    private void addCivilian(Civilian civilian) {
        people.put(civilian.getUUID(), civilian);
        civilians.put(civilian.getUUID(), civilian);
    }

    private void addPhysician(Physician physician) {
        people.put(physician.getUUID(), physician);
        physicians.put(physician.getUUID(), physician);
    }

    private void removeCivilian(Civilian civilian) {
        people.remove(civilian.getUUID());
        civilians.remove(civilian.getUUID());
    }

    private void removePhysician(Physician physician) {
        people.remove(physician.getUUID());
        physicians.remove(physician.getUUID());
    }

    private void updateCivilian(Civilian civilian) {
        removeCivilian(civilian);
        addCivilian(civilian);
    }

    private void updatePhysician(Physician physician) {
        removePhysician(physician);
        addPhysician(physician);
    }
}
