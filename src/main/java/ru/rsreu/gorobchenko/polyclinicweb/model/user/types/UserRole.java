package ru.rsreu.gorobchenko.polyclinicweb.model.user.types;

import ru.rsreu.gorobchenko.polyclinicweb.resource.ConfigurationManager;

public enum UserRole {
    SYSTEM_ADMINISTRATOR,
    MODERATOR,
    ADMINISTRATOR,
    DOCTOR,
    PATIENT;

    private static final String ROLE_CONFIGURATION_ID_PATTERN = "db.user.role.%s.id";
    private static final String ROLE_CONFIGURATION_NAME_PATTERN = "db.user.role.%s.name";

    private final int databaseRoleId;
    private final String roleName;

    UserRole() {
        String enumValueName = this.name().toLowerCase();
        String configurationIdPattern = String.format(ROLE_CONFIGURATION_ID_PATTERN, enumValueName);
        this.databaseRoleId = Integer.parseInt(ConfigurationManager.get(configurationIdPattern));
        String configurationNamePattern = String.format(ROLE_CONFIGURATION_NAME_PATTERN, enumValueName);
        this.roleName = ConfigurationManager.get(configurationNamePattern);
    }

    public static UserRole construct(int databaseRoleId) {
        for (UserRole role : values()) {
            if (role.databaseRoleId == databaseRoleId) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown database role id: " + databaseRoleId);
    }

    public boolean isSystemObservable() {
        return isSystemAdministrator() || isModerator();
    }

    public boolean isEmployee() {
        return isAdministrator() || isDoctor();
    }

    public boolean isSystemAdministrator() {
        return UserRole.SYSTEM_ADMINISTRATOR == this;
    }

    public boolean isModerator() {
        return UserRole.MODERATOR == this;
    }

    public boolean isAdministrator() {
        return UserRole.ADMINISTRATOR == this;
    }

    public boolean isDoctor() {
        return UserRole.DOCTOR == this;
    }

    public boolean isPatient() {
        return UserRole.PATIENT == this;
    }

    public int getDatabaseRoleId() {
        return databaseRoleId;
    }

    public String getName() {
        return roleName;
    }

    @Override
    public String toString() {
        return getName();
    }
}
