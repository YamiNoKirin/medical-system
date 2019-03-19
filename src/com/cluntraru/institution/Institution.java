package com.cluntraru.institution;

import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.person.Person;

import java.util.*;

abstract public class Institution {
    private static int institutionCount = 0;
    private final int id = institutionCount;

    private SortedSet<Person> staffSet;

    Institution(ManagementAuthority managementAuthority) {
        // TODO (CL): record
        staffSet = new TreeSet<>(Comparator.comparingInt(Person::getId));
        ++institutionCount;
    }

    public List<Person> getStaff() {
        return new ArrayList<>(staffSet);
    }

    protected void addStaff(ManagementAuthority managementAuthority, Person person) {
        // TODO (CL): record
        staffSet.add(person);
    }

    protected void removeStaff(ManagementAuthority managementAuthority, Person person) {
        // TODO (CL): record
        staffSet.remove(person);
    }

    public int getId() {
        return id;
    }
}
