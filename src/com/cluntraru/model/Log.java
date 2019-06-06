package com.cluntraru.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Log {
    UUID uuid;
    Timestamp timestamp;
    String request;

    public Log(UUID uuid, Timestamp timestamp, String request) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.request = request;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getRequest() {
        return request;
    }

    public String toString() {
        return uuid.toString() + " " + timestamp.toString() + " " + request;
    }
}
