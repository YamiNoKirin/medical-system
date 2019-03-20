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
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();
        Hospital hospital = (Hospital) mgmtAuthority.makeRequest(RequestType.NEW_INSTITUTION, InstitutionType.HOSPITAL);
        Physician physician = (Physician) mgmtAuthority.makeRequest(RequestType.NEW_PERSON, PersonType.PHYSICIAN,
                                                                    "Joe", hospital);
        Civilian civilian = (Civilian) mgmtAuthority.makeRequest(RequestType.NEW_PERSON, PersonType.CIVILIAN,
                                                                 "Steve");

        mgmtAuthority.makeRequest(RequestType.PERSON_SICK, civilian, hospital);
        mgmtAuthority.makeRequest(RequestType.PERSON_SICK, physician, hospital);
        System.out.println(physician.getInstitution());
        mgmtAuthority.makeRequest(RequestType.PERSON_HEAL, physician);
        System.out.println(physician.getInstitution());
    }
}
