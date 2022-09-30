package com.springpageable.enums;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface Describable {

    AbstractDetails withDetails();

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    abstract class AbstractDetails {

        private int id;

        private Object name;
    }
}
