package com.cluntraru.service.logger;

import com.cluntraru.service.authority.RequestType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Logger {
    private File file;

    public Logger() {
        file = new File("log.csv");
    }

    public void logRequest(RequestType requestType) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(timestamp + " " + requestType.toString() + ", ");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
