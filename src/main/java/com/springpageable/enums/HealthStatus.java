package com.springpageable.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public enum HealthStatus implements Describable {

    ALRIGHT("Alright"),     //0
    OBSERVE("Observe"),     //1
    CRITICAL("Critical"),   //2
    NOT_APPLICABLE("N/A");  //3

    @Getter
    private final String value;

    private static final HealthStatus[] values = values();

    @Override
    public Details withDetails() {
        return new Details(ordinal(), value);
    }

    @NoArgsConstructor
    public static class Details extends AbstractDetails {
        public Details(int id, String name) {
            super(id, name);
        }
    }

    /**
     * Converts ordinal of componentStatus to ComponentStatus
     *
     * @param value - the ordinal of {@link HealthStatus}
     * @return {@link HealthStatus} or null
     */
    public static Details getByValue(Integer value) {
        if (value == null || value < 0 || value >= values.length) {
            return null;
        }
        return values[value].withDetails();
    }
}
