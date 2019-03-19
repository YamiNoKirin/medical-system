package com.cluntraru.admin_entity;

public final class AdminEntity {
    private static AdminEntity instance;

    private AdminEntity() {
        // TODO (CL): constructor
    }

    public static AdminEntity getInstance() {
        if (instance == null) {
            instance = new AdminEntity();
        }

        return instance;
    }
}
