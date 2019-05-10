package com.cluntraru.service.databasemanager;
import com.cluntraru.model.institution.Hospital;
import com.cluntraru.model.institution.Institution;
import com.cluntraru.model.person.Civilian;
import com.cluntraru.model.person.Person;
import com.cluntraru.model.person.Physician;
import com.cluntraru.model.prescription.Prescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JDBCManager {
    private static JDBCManager instance;

    public static JDBCManager getInstance() {
        if (instance == null) {
            instance = new JDBCManager();
        }

        return instance;
    }

    private final String driver = "jdbc:mysql://localhost:3306/medical?serverTimezone=UTC";
    private final String user = "myusr";
    private final String passwd = "myusr";

    private JDBCManager() {}

    private String getPersonType(Person person) {
        if (person instanceof Civilian) {
            return "'Civilian'";
        }
        else if (person instanceof Physician) {
            return "'Physician'";
        }

        return null;
    }

    private String getInstitGUID(Person person) {
        if (person.getInstitution() != null) {
            return "'" + person.getInstitution().getUUID() + "'";
        }

        return null;
    }

    private String getInstitutionType(Institution instit) {
        if (instit instanceof Hospital) {
            return "'Hospital'";
        }

        return null;
    }

    private Person personFromResultSet(ResultSet rs) {
        try {
            String type = rs.getString("Type");
            String name = rs.getString("Name");

            String hospitalGUIDString = rs.getString("InstitutionGUID");
            Hospital hospital = null;
            if (hospitalGUIDString != null) {
                hospital = (Hospital) getInstitution(UUID.fromString(hospitalGUIDString));
            }

            Boolean isAlive = rs.getBoolean("Alive");
            Boolean isSick = rs.getBoolean("Sick");
            UUID uuid = UUID.fromString(rs.getString("PersonGUID"));

            Person person = null;
            if (type.equals("Civilian")) {
                person = new Civilian(name, hospital, isAlive, isSick, uuid);
            }
            else if (type.equals("Physician")) {
                person = new Physician(name, hospital, isAlive, isSick, uuid);
            }

            return person;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    private Institution institutionFromResultSet(ResultSet rs) {
        try {
            String type = rs.getString("Type");
            UUID uuid = UUID.fromString(rs.getString("InstitutionGUID"));

            Institution instit = null;
            if (type.equals("Hospital")) {
                instit = new Hospital(uuid);
            }

            return instit;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    private Prescription prescriptionFromResultSet(ResultSet rs) {
        try {
            String medName = rs.getString("MedName");
            Person person = getPerson(UUID.fromString(rs.getString("PatientGUID")));
            Boolean isActive = rs.getBoolean("Active");
            UUID uuid = UUID.fromString(rs.getString("PrescriptionGUID"));

            Prescription presc = new Prescription(medName, person, isActive, uuid);
            return presc;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void addPerson(Person person) {
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "INSERT INTO people VALUES(" +
                            "'" + person.getUUID() + "', " +
                            "'" + person.getName() + "', " +
                            getPersonType(person) + ", " +
                            getInstitGUID(person) + ", " +
                            person.isAlive() + ", "+
                            person.isSick() + " " +
                            ");";
            stmt.executeUpdate(query);

            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addInstitution(Institution instit) {
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "INSERT INTO institutions VALUES(" +
                            "'" + instit.getUUID() + "', " +
                            getInstitutionType(instit) + " " +
                            ");";
            stmt.executeUpdate(query);

            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addPrescription(Prescription presc) {
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "INSERT INTO prescriptions VALUES(" +
                            "'" + presc.getUUID() + "', " +
                            "'" + presc.getMedicineName() + "', " +
                            "'" + presc.getPrescribedTo().getUUID() + "', " +
                            presc.getIsActive() + " " +
                            ");";
            stmt.executeUpdate(query);

            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updatePerson(Person person) {
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "UPDATE people SET " +
                            "Name='" + person.getName() + "'," +
                            "Type=" + getPersonType(person) + "," +
                            "InstitutionGUID=" + getInstitGUID(person) + ", " +
                            "Alive=" + person.isAlive() + ", " +
                            "Sick=" + person.isSick() + " " +
                            "WHERE " +
                            "PersonGUID='" + person.getUUID() + "' " +
                            ";";
            stmt.executeUpdate(query);

            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateInstitution(Institution instit) {
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "UPDATE institutions SET " +
                            "Type=" + getInstitutionType(instit) + " " +
                            "WHERE " +
                            "InstitutionGUID='" + instit.getUUID() + "' " +
                            ";";
            stmt.executeUpdate(query);

            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updatePrescription(Prescription presc) {
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "UPDATE prescriptions SET " +
                            "MedName='" + presc.getMedicineName() + "', " +
                            "PatientGUID='" + presc.getPrescribedTo().getUUID() + "', " +
                            "Active=" + presc.getIsActive() + " " +
                            "WHERE " +
                            "PrescriptionGUID='" + presc.getUUID() + "' " +
                            ";";

            stmt.executeUpdate(query);

            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Person> getPeople() {
        List<Person> personList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM people;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Person person = personFromResultSet(rs);
                if (person != null) {
                    personList.add(person);
                }
            }

            conn.close();
            return personList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Physician> getPhysicians() {
        List<Physician> personList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM people WHERE Type='Physician';";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Physician person = (Physician) personFromResultSet(rs);
                if (person != null) {
                    personList.add(person);
                }
            }

            conn.close();

            return personList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Civilian> getCivilians() {
        List<Civilian> personList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM people WHERE Type='Civilian';";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Civilian person = (Civilian) personFromResultSet(rs);
                if (person != null) {
                    personList.add(person);
                }
            }

            conn.close();
            return personList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Prescription> getIssuedPrescriptions() {
        List<Prescription> prescList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM prescriptions;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Prescription presc = prescriptionFromResultSet(rs);
                if (presc != null) {
                    prescList.add(presc);
                }
            }

            conn.close();
            return prescList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Institution> getInstitutions() {
        List<Institution> institList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM institutions;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Institution instit = institutionFromResultSet(rs);
                if (instit != null) {
                    institList.add(instit);
                }
            }

            conn.close();
            return institList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Hospital> getHospitals() {
        List<Hospital> hospitalList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM institutions WHERE Type='Hospital';";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Hospital hospital = (Hospital) institutionFromResultSet(rs);
                if (hospital != null) {
                    hospitalList.add(hospital);
                }
            }

            conn.close();
            return hospitalList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Person> getInstitutionStaff(Institution instit) {
        List<Person> personList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM people WHERE Type='Physician' AND InstitutionGUID='" + instit.getUUID() + "';";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Person person = personFromResultSet(rs);
                if (person != null) {
                    personList.add(person);
                }
            }

            conn.close();
            return personList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public List<Person> getInstitutionPatients(Institution instit) {
        List<Person> personList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM people WHERE Type='Civilian' AND InstitutionGUID='" + instit.getUUID() + "';";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Person person = personFromResultSet(rs);
                if (person != null) {
                    personList.add(person);
                }
            }

            conn.close();
            return personList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public Institution getInstitution(UUID uuid) {
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM institutions WHERE InstitutionGUID='" +
                            uuid + "';";
            ResultSet rs = stmt.executeQuery(query);

            Institution instit = null;
            if (rs.next()) {
                instit = institutionFromResultSet(rs);
            }

            conn.close();
            return instit;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public Person getPerson(UUID uuid) {
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM people WHERE PersonGUID='" + uuid + "';";
            ResultSet rs = stmt.executeQuery(query);

            Person person = null;
            if (rs.next()) {
                person = personFromResultSet(rs);
            }

            conn.close();
            return person;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public Prescription getPrescription(UUID uuid) {
        try {
            Connection conn = DriverManager.getConnection(driver, user, passwd);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM prescriptions WHERE PrescriptionGUID='" +
                            uuid + "';";
            ResultSet rs = stmt.executeQuery(query);

            Prescription presc = null;
            if (rs.next()) {
                presc = prescriptionFromResultSet(rs);
            }

            conn.close();
            return presc;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
