package com.cluntraru.service.authority;

import com.cluntraru.model.institution.Hospital;
import com.cluntraru.model.institution.Institution;
import com.cluntraru.model.person.Person;
import com.cluntraru.service.databasemanager.JDBCManager;

import java.util.*;

public class InstitutionAuthority {
    List<Institution> getAll() {
        return JDBCManager.getInstance().getInstitutions();
    }

    List<Hospital> getHospitals() {
        return JDBCManager.getInstance().getHospitals();
    }

    List<Person> getInstitutionStaff(Institution instit) {
        return JDBCManager.getInstance().getInstitutionStaff(instit);
    }

    List<Person> getInstitutionPatients(Institution instit) {
        return JDBCManager.getInstance().getInstitutionPatients(instit);
    }

    Institution getInstitution(UUID uuid) {
        if (uuid != null) {
            return JDBCManager.getInstance().getInstitution(uuid);
        }

        return null;
    }

    void record(Institution institution) {
        if (JDBCManager.getInstance().getInstitution(institution.getUUID()) != null) {
            JDBCManager.getInstance().updateInstitution(institution);
        }
        else {
            JDBCManager.getInstance().addInstitution(institution);
        }
    }
}
