package com.cluntraru.model.institution;

import com.cluntraru.model.person.Person;

import java.util.*;

abstract public class Institution {
    private UUID uuid;

    protected Institution() {
        uuid = UUID.randomUUID();
    }

    protected Institution(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return uuid;
    }
}
