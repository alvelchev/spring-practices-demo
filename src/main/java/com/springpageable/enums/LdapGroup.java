package com.springpageable.enums;

/**
 * Important! Don't change the ordering of the enum values since it will be a breaking change in the consuming services.
 */
public enum LdapGroup {

    ADMIN(UserRole.ADMIN),                     // 0
    HIGH_PRIVILEGED(UserRole.HIGH_PRIVILEGED), // 1
    LOW_PRIVILEGED(UserRole.LOW_PRIVILEGED),   // 2
    GENERAL(UserRole.GENERAL);                       // 3
    private static final LdapGroup[] values = LdapGroup.values();

    private final String name;

    LdapGroup(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static LdapGroup parseByOrdinal(String ordinal) {
        return values[Integer.parseInt(ordinal)];
    }
}
