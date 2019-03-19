package com.cluntraru;

import com.cluntraru.institution.Hospital;
import com.cluntraru.institution.InstitutionType;
import com.cluntraru.management.ManagementAuthority;
import com.cluntraru.management.RequestType;
import com.cluntraru.person.Civilian;
import com.cluntraru.person.PersonType;
import com.cluntraru.person.Physician;

public class Main {

    public static void main(String[] args) {
        ManagementAuthority managementAuthority = ManagementAuthority.getInstance();
        Physician physician = (Physician) managementAuthority.makeRequest(RequestType.NEW_PERSON, PersonType.PHYSICIAN, "Joe");
        Civilian civilian = (Civilian) managementAuthority.makeRequest(RequestType.NEW_PERSON, PersonType.CIVILIAN, "Steve");
        Hospital hospital = (Hospital) managementAuthority.makeRequest(RequestType.NEW_INSTITUTION, InstitutionType.HOSPITAL);

        managementAuthority.makeRequest(RequestType.PERSON_SICK, civilian, hospital);
        System.out.println(managementAuthority.getPeople());
    }
}
