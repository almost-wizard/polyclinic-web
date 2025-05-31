package ru.rsreu.gorobchenko.polyclinicweb.model.user.types;

import ru.rsreu.gorobchenko.polyclinicweb.resource.ConfigurationManager;

public enum UserState {
    ACTIVE,
    AUTHORIZED,
    BLOCKED;

    private static final String ROLE_CONFIGURATION_NAME_PATTERN = "db.user.status.%s.name";

    private final String name;

    UserState() {
        String enumValueName = this.name().toLowerCase();
        String configurationNamePattern = String.format(ROLE_CONFIGURATION_NAME_PATTERN, enumValueName);
        this.name = ConfigurationManager.get(configurationNamePattern);
    }

    public static UserState construct(boolean isAuthorized, boolean isBlocked) {
        if (isBlocked) {
            return BLOCKED;
        } else if (isAuthorized) {
            return AUTHORIZED;
        }
        return ACTIVE;
    }

    public static UserState construct(String status) {
        if (status == null) {
            return null;
        }
        switch (status) {
            case "blocked":
                return BLOCKED;
            case "authorized":
                return AUTHORIZED;
            case "unblocked":
                return ACTIVE;
        }
        return null;
    }

    public boolean isAuthorized() {
        return this == AUTHORIZED;
    }

    public boolean isBlocked() {
        return this == BLOCKED;
    }

    public boolean isActive() {
        return this == ACTIVE;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return getName();
    }
}
