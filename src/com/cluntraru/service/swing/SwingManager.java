package com.cluntraru.service.swing;

import com.cluntraru.model.institution.Hospital;
import com.cluntraru.model.institution.InstitutionType;
import com.cluntraru.model.person.Person;
import com.cluntraru.model.person.PersonType;
import com.cluntraru.model.prescription.Prescription;
import com.cluntraru.service.authority.ManagementAuthority;
import com.cluntraru.service.authority.RequestRunnable;
import com.cluntraru.service.authority.RequestType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingManager extends JFrame {
    private static SwingManager instance;

    public static SwingManager getInstance() {
        if (instance == null) {
            instance = new SwingManager();
        }

        return instance;
    }

    private SwingManager() {}

    public void init() {
        JFrame frame = new JFrame("Medical System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 100);
        frame.setLayout(new FlowLayout());


        JPanel makePanel = initMakePanel();
        makePanel.setVisible(true);

        JPanel getPanel = initGetPanel();
        getPanel.setVisible(false);

        JButton getPanelButton = new JButton("'GET' Actions");
        getPanelButton.addActionListener(actionEvent -> {
            makePanel.setVisible(false);
            getPanel.setVisible(true);
        });

        makePanel.add(getPanelButton);

        JButton makePanelButton = new JButton("'Make' Actions");
        makePanelButton.addActionListener(actionEvent -> {
            getPanel.setVisible(false);
            makePanel.setVisible(true);
        });

        getPanel.add(makePanelButton);

        frame.add(makePanel);
        frame.add(getPanel);

        frame.setVisible(true);
    }

    private JPanel initMakePanel() {
        JButton newCivilianButton = new JButton("New civilian");
        newCivilianButton.addActionListener(actionEvent -> makeRandomCivilian());

        JButton newPhysicianButton = new JButton("New physician");
        newPhysicianButton.addActionListener(actionEvent -> makeRandomPhysician());

        JButton newHospitalButton = new JButton("New hospital");
        newHospitalButton.addActionListener(actionEvent -> makeRandomHospital());

        JButton killPersonButton = new JButton("Kill person");
        killPersonButton.addActionListener(actionEvent -> killRandomPerson());

        JButton infectPersonButton = new JButton("Infect person");
        infectPersonButton.addActionListener(actionEvent -> infectRandomPerson());

        JButton demoButton = new JButton("Run Demo");
        demoButton.addActionListener(actionEvent -> runDemo());

        JPanel makePanel = new JPanel();
        makePanel.add(newCivilianButton);
        makePanel.add(newPhysicianButton);
        makePanel.add(newHospitalButton);
        makePanel.add(killPersonButton);
        makePanel.add(infectPersonButton);
        makePanel.add(demoButton);

        return makePanel;
    }

    private JPanel initGetPanel() {
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();

        JButton getPeopleButton = new JButton("Get people");
        getPeopleButton.addActionListener(actionEvent -> System.out.println(mgmtAuthority.getPeople()));

        JButton getInstitutionsButton = new JButton("Get institutions");
        getInstitutionsButton.addActionListener(actionEvent -> System.out.println(mgmtAuthority.getInstitutions()));

        JButton getPrescriptionsButton = new JButton("Get prescriptions");
        getPrescriptionsButton.addActionListener(actionEvent -> System.out.println(mgmtAuthority.getIssuedPrescriptions()));

        JPanel getPanel = new JPanel();
        getPanel.add(getPeopleButton);
        getPanel.add(getInstitutionsButton);
        getPanel.add(getPrescriptionsButton);

        return getPanel;
    }

    private void makeRandomHospital() {
        Thread t = new Thread(new RequestRunnable(RequestType.NEW_INSTITUTION, InstitutionType.HOSPITAL));
        t.start();
    }

    private void makeRandomPhysician() {
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();
        Thread t = new Thread(new RequestRunnable(RequestType.NEW_PERSON, PersonType.PHYSICIAN,
                "Phy" + (int) Math.random() * 1000,
                mgmtAuthority.getRandomHospital()));

        t.start();
    }

    private void makeRandomCivilian() {
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();
        Thread t = new Thread(new RequestRunnable(RequestType.NEW_PERSON, PersonType.CIVILIAN,
                "Civ" + (int) Math.random() * 1000));
        t.start();
    }

    private void killRandomPerson() {
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();
        Thread t = new Thread(new RequestRunnable(RequestType.PERSON_DIE, mgmtAuthority.getRandomPerson()));
        t.start();
    }

    private void infectRandomPerson() {
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();
        Thread t = new Thread(new RequestRunnable(RequestType.PERSON_SICK, mgmtAuthority.getRandomLivePerson(),
                mgmtAuthority.getRandomHospital()));
        t.start();
    }

    private void runDemo() {
        ManagementAuthority mgmtAuthority = ManagementAuthority.getInstance();

        // Create hospitals
        for (int i = 0; i < 3; ++i) {
            mgmtAuthority.makeRequest(RequestType.NEW_INSTITUTION, InstitutionType.HOSPITAL);
        }
        // Create physicians
        for (Integer i = 0; i < 10; ++i) {
            mgmtAuthority.makeRequest(RequestType.NEW_PERSON, PersonType.PHYSICIAN, "Phy" + i.toString(),
                                      mgmtAuthority.getRandomHospital());
        }

        // Create civilians
        for (Integer i = 0; i < 50; ++i) {
            mgmtAuthority.makeRequest(RequestType.NEW_PERSON, PersonType.CIVILIAN, "Civ" + i.toString());
        }

        // Issue prescriptions
        for (Integer i = 0; i < 10; ++i) {
            mgmtAuthority.makeRequest(RequestType.NEW_PRESCRIPTION, "Med" + i.toString(),
                                      mgmtAuthority.getRandomPerson());
        }

        // Archive prescriptions
        for (int i = 0; i < 3; ++i) {
            Prescription prescription = mgmtAuthority.getRandomActivePrescription();
            mgmtAuthority.makeRequest(RequestType.PERSON_REDEEM_PRESCRIPTION, prescription.getPrescribedTo(),
                                      prescription);
        }

        // Kill people
        for (int i = 0; i < 5; ++i) {
            mgmtAuthority.makeRequest(RequestType.PERSON_DIE, mgmtAuthority.getRandomPerson());
        }

        // Infect people
        for (int i = 0; i < 15; ++i) {
            mgmtAuthority.makeRequest(RequestType.PERSON_SICK, mgmtAuthority.getRandomLivePerson(),
                                      mgmtAuthority.getRandomHospital());
        }

        Person deadPerson = mgmtAuthority.getRandomPerson();
        mgmtAuthority.makeRequest(RequestType.PERSON_DIE, deadPerson);

        Person healedPerson = mgmtAuthority.getRandomPerson();
        Hospital hospital = mgmtAuthority.getRandomHospital();
        while (mgmtAuthority.getInstitutionStaff(hospital).isEmpty()) {
            hospital = mgmtAuthority.getRandomHospital();
        }

        mgmtAuthority.makeRequest(RequestType.PERSON_SICK, healedPerson, hospital);
        mgmtAuthority.makeRequest(RequestType.PERSON_HEAL, healedPerson);

        System.out.println("All people: " + mgmtAuthority.getPeople().toString());
        System.out.println("Live people: " + mgmtAuthority.getLivePeople().toString());
        System.out.println("Deceased people: " + mgmtAuthority.getDeceasedPeople().toString());

        System.out.println("Healthy people: " + mgmtAuthority.getHealthyPeople().toString());
        System.out.println("Sick people: " + mgmtAuthority.getSickPeople().toString());

        System.out.println("Physicians: " + mgmtAuthority.getPhysicians().toString());
        System.out.println("Civilians: " + mgmtAuthority.getCivilians().toString());

        System.out.println("Institutions: " + mgmtAuthority.getInstitutions().toString());
        System.out.println("Hospitals: " + mgmtAuthority.getHospitals().toString());

        System.out.println("Prescriptions: " + mgmtAuthority.getIssuedPrescriptions().toString());
        System.out.println("Active prescriptions: " + mgmtAuthority.getActivePrescriptions().toString());
        System.out.println("Archived prescriptions: " + mgmtAuthority.getArchivedPrescriptions().toString());

        // Change staff
        Hospital hospital1 = mgmtAuthority.getRandomHospital();
        while (hospital1 == hospital) { // Compares reference
            hospital1 = mgmtAuthority.getRandomHospital();
        }

        System.out.println("Hospital staff: " + mgmtAuthority.getInstitutionStaff(hospital));
        System.out.println("Other hospital staff: " + mgmtAuthority.getInstitutionStaff(hospital1));

        Person staffMember = mgmtAuthority.getInstitutionStaff(hospital).get(0);
        mgmtAuthority.makeRequest(RequestType.INSTITUTION_REMOVE_STAFF, hospital, staffMember);
        mgmtAuthority.makeRequest(RequestType.INSTITUTION_ADD_STAFF, hospital1, staffMember);

        System.out.println("After move - hospital staff: " + mgmtAuthority.getInstitutionStaff(hospital));
        System.out.println("After move - other hospital staff: " + mgmtAuthority.getInstitutionStaff(hospital1));

        System.out.println("Hospital patients: " + mgmtAuthority.getInstitutionPatients(hospital));
    }
}
