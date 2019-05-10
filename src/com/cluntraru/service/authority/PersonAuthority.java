package com.cluntraru.service.authority;

import com.cluntraru.model.person.Civilian;
import com.cluntraru.model.person.Person;
import com.cluntraru.model.person.Physician;
import com.cluntraru.service.databasemanager.JDBCManager;
import jdk.nashorn.internal.scripts.JD;

import java.util.*;

public class PersonAuthority {
    List<Person> getAll() {
        return JDBCManager.getInstance().getPeople();
    }

    List<Person> getAlive() {
        List<Person> people = JDBCManager.getInstance().getPeople();
        List<Person> aliveList = new ArrayList<>();
        for (Person person: people) {
            if (person.isAlive()) {
                aliveList.add(person);
            }
        }

        return aliveList;
    }

    List<Person> getDeceased() {
        List<Person> people = JDBCManager.getInstance().getPeople();
        List<Person> deceasedList = new ArrayList<>();
        for (Person person: people) {
            if (!person.isAlive()) {
                deceasedList.add(person);
            }
        }

        return deceasedList;
    }

    List<Person> getSick() {
        List<Person> people = JDBCManager.getInstance().getPeople();
        List<Person> sickList = new ArrayList<>();
        for (Person person: people) {
            if (person.isAlive() && person.isSick()) {
                sickList.add(person);
            }
        }

        return sickList;
    }

    List<Person> getHealthy() {
        List<Person> people = JDBCManager.getInstance().getPeople();
        List<Person> healthyList = new ArrayList<>();
        for (Person person: people) {
            if (person.isAlive() && !person.isSick()) {
                healthyList.add(person);
            }
        }

        return healthyList;
    }

    Person getPerson(UUID uuid) {
        if (uuid != null) {
            return JDBCManager.getInstance().getPerson(uuid);
        }

        return null;
    }

    List<Civilian> getCivilians() {
        return JDBCManager.getInstance().getCivilians();
    }

    List<Physician> getPhysicians() {
        return JDBCManager.getInstance().getPhysicians();
    }

    void record(Person person) {
        if (JDBCManager.getInstance().getPerson(person.getUUID()) != null) {
            JDBCManager.getInstance().updatePerson(person);
        }
        else {
            JDBCManager.getInstance().addPerson(person);
        }
    }
}
