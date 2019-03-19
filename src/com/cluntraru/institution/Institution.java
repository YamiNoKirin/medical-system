package com.cluntraru.institution;

import com.cluntraru.admin_entity.AdminEntity;
import com.cluntraru.person.Person;

import java.util.*;

public class Institution {
    private static int institutionCount = 0;
    private final int id = institutionCount;

    private SortedSet<Person> staffSet;

    Institution(AdminEntity adminEntity) {
        // TODO (CL): record
        staffSet = new TreeSet<>(Comparator.comparingInt(Person::getId));
        ++institutionCount;
    }

    public List<Person> getStaff() {
        return new ArrayList<>(staffSet);
    }

    protected void addStaff(AdminEntity adminEntity, Person person) {
        // TODO (CL): record
        staffSet.add(person);
    }

    protected void removeStaff(AdminEntity adminEntity, Person person) {
        // TODO (CL): record
        staffSet.remove(person);
    }

    public int getId() {
        return id;
    }
}
