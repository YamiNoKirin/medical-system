package com.cluntraru.service.authority;

import com.cluntraru.model.prescription.Prescription;
import com.cluntraru.service.databasemanager.JDBCManager;

import javax.sql.rowset.JdbcRowSet;
import java.util.*;

public class PrescriptionAuthority {
    List<Prescription> getAll() {
        return JDBCManager.getInstance().getIssuedPrescriptions();
    }

    List<Prescription> getActive() {
        List<Prescription> prescriptions = JDBCManager.getInstance().getIssuedPrescriptions();
        List<Prescription> activeList = new ArrayList<>();
        for (Prescription presc: prescriptions) {
            if (presc.getIsActive()) {
                activeList.add(presc);
            }
        }

        return activeList;
    }

    List<Prescription> getArchived() {
        List<Prescription> prescriptions = JDBCManager.getInstance().getIssuedPrescriptions();
        List<Prescription> archivedList = new ArrayList<>();
        for (Prescription presc: prescriptions) {
            if (!presc.getIsActive()) {
                archivedList.add(presc);
            }
        }

        return archivedList;
    }

    Prescription getPrescription(UUID uuid) {
        if (uuid == null) {
            return null;
        }

        return JDBCManager.getInstance().getPrescription(uuid);
    }

    void record(Prescription prescription) {
        if (JDBCManager.getInstance().getPrescription(prescription.getUUID()) != null) {
            JDBCManager.getInstance().updatePrescription(prescription);
        }
        else {
            JDBCManager.getInstance().addPrescription(prescription);
        }
    }
}
