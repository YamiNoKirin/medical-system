package com.cluntraru.service.datamanager;

import com.cluntraru.model.institution.Hospital;
import com.cluntraru.model.institution.Institution;
import com.cluntraru.model.person.Civilian;
import com.cluntraru.model.person.Person;
import com.cluntraru.model.person.Physician;
import com.cluntraru.model.prescription.Prescription;
import com.cluntraru.service.authority.ManagementAuthority;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CSVDataManager implements IDataManager{
    private File models_csv;
    private File person_csv;
    private File institution_csv;
    private File prescription_csv;

    public CSVDataManager() {
        models_csv = new File("./models.csv");
        person_csv = new File("./person.csv");
        institution_csv = new File("./institution.csv");
        prescription_csv = new File("./prescription.csv");
    }

    @Override
    public void saveData() throws IOException {
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();
        List<Person> personList = mgmtAuthority.getPeople();
        recordPeople(personList);

        List<Institution> institList = mgmtAuthority.getInstitutions();
        recordInstitutions(institList);

        List<Prescription> prescList = mgmtAuthority.getIssuedPrescriptions();
        recordPrescriptions(prescList);
    }

    @Override
    public void loadData() throws IOException {
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();
        // Order matters!
        List<Institution> institList = loadInstitutions();
        for (Institution instit: institList) {
            mgmtAuthority.loadInstitution(instit);
        }

        List<Person> personList = loadPeople();
        for (Person person: personList) {
            mgmtAuthority.loadPerson(person);
        }

        List<Prescription> prescList = loadPrescriptions();
        for (Prescription presc: prescList) {
            mgmtAuthority.loadPrescription(presc);
        }
    }

//    private String readEntry(Scanner scanner) throws IOException {
//        if (!scanner.hasNext()) {
//            return null;
//        }
//
//        String entry = "";
//        String token = scanner.next();
//        while (scanner.hasNext() && !token.endsWith(",")) {
//            entry += token;
//            token = scanner.next();
//        }
//
//        return entry;
//    }

    private List<Person> loadPeople() throws IOException {
        List<Person> personList = new ArrayList<>();

        Scanner scanner = new Scanner(person_csv);
        while (scanner.hasNext()) {
            UUID uuid = UUID.fromString(scanner.next());
            String name = scanner.next();
            String type = scanner.next();
            UUID institUUID;
            String institUUIDString = scanner.next();
            if (institUUIDString.equals("null")) {
                institUUID = null;
            }
            else {
                institUUID = UUID.fromString(institUUIDString);
            }
            boolean isAlive = scanner.nextBoolean();
            String isSickString = scanner.next();
            boolean isSick = Boolean.parseBoolean(isSickString.substring(0, isSickString.length() - 1));

            Institution instit = ManagementAuthority.getInstance().getInstitution(institUUID);

            Person person = null;
            if (type.equals("civilian")) {
                person = new Civilian(name, (Hospital) instit, isAlive, isSick, uuid);
            }
            else if (type.equals("physician")) {
                person = new Physician(name, (Hospital) instit, isAlive, isSick, uuid);
            }

            if (person != null) {
                personList.add(person);
            }
        }

        scanner.close();
        return personList;
    }

    private List<Institution> loadInstitutions() throws IOException {
        List<Institution> institList = new ArrayList<>();

        Scanner scanner = new Scanner(institution_csv);
        while (scanner.hasNext()) {
            UUID uuid = UUID.fromString(scanner.next());
            String type = scanner.next();
            type = type.substring(0, type.length() - 1);

            Institution instit = null;
            if (type.equals("hospital")) {
                instit = new Hospital(uuid);
            }

            if (instit != null) {
                institList.add(instit);
            }
        }

        scanner.close();
        return institList;
    }

    private List<Prescription> loadPrescriptions() throws IOException {
        List<Prescription> prescList = new ArrayList<>();

        Scanner scanner = new Scanner(prescription_csv);
        while (scanner.hasNext()) {
            UUID uuid = UUID.fromString(scanner.next());
            String medName = scanner.next();
            UUID personUUID = UUID.fromString(scanner.next());
            String isActiveString = scanner.next();
            boolean isActive = Boolean.parseBoolean(isActiveString.substring(0, isActiveString.length() - 1));

            Person person = ManagementAuthority.getInstance().getPerson(personUUID);
            Prescription presc = new Prescription(medName, person, isActive, uuid);
            prescList.add(presc);
        }

        scanner.close();
        return prescList;
    }

    private void writePerson(FileWriter fw, Person person) throws IOException {
        String type = null;
        if (person instanceof Civilian) {
            type = "civilian";
        }
        else if (person instanceof Physician) {
            type = "physician";
        }

        String institUUID = null;
        if (person.getInstitution() != null) {
            institUUID = person.getInstitution().getUUID().toString();
        }

        fw.write(person.getUUID() + " " + person.getName() + " " + type + " " + institUUID + " " +
                person.isAlive() + " " + person.isSick() + ", ");
    }

    private void writeInstitution(FileWriter fw, Institution instit) throws IOException {
        String type = null;
        if (instit instanceof Hospital) {
            type = "hospital";
        }

        fw.write(instit.getUUID() + " " + type + ", ");
    }

    private void writePrescription(FileWriter fw, Prescription presc) throws IOException {
        fw.write(presc.getUUID() + " " + presc.getMedicineName() + " " + presc.getPrescribedTo().getUUID() + " " +
                presc.getIsActive() + ", ");
    }

    private void recordModels(List<Person> personList, List<Institution> institList, List<Prescription> prescList)
            throws IOException {
        FileWriter fw = new FileWriter(models_csv, false);
        for (Person person: personList) {
            fw.write("person " + person.getUUID());
        }

        for (Institution instit: institList) {
            fw.write("institution " + instit.getUUID());
        }

        for (Prescription presc: prescList) {
            fw.write("prescription " + presc.getUUID());
        }

        fw.flush();
        fw.close();
    }

    private void recordPeople(List<Person> personList) throws IOException {
        FileWriter fw = new FileWriter(person_csv, false);
        for (Person person: personList) {
            writePerson(fw, person);
        }

        fw.flush();
        fw.close();
    }

    private void recordInstitutions(List<Institution> institList) throws IOException {
        FileWriter fw = new FileWriter(institution_csv, false);
        for (Institution instit: institList) {
            writeInstitution(fw, instit);
        }

        fw.flush();
        fw.close();
    }

    private void recordPrescriptions(List<Prescription> prescList) throws IOException {
        FileWriter fw = new FileWriter(prescription_csv, false);
        for (Prescription presc: prescList) {
            writePrescription(fw, presc);
        }

        fw.flush();
        fw.close();
    }
}
