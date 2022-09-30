package com.springpageable.enums;

import lombok.Getter;

public enum RegionType {
  COUNTRIES_CE(1),
  COUNTRIES_FRENCH_OVERSEAS(2),
  NON_EXISTING_REGION_TYPE(-1);

  @Getter private final Integer value;

  private static final RegionType[] regionType = values();

  RegionType(Integer value) {
    this.value = value;
  }

  public static RegionType getEnum(Integer value) {
    for (RegionType e : regionType) {
      if (e.value.equals(value)) {
        return e;
      }
    }
    return NON_EXISTING_REGION_TYPE;
  }
}
