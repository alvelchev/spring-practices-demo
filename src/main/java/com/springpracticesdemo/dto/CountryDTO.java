package com.springpracticesdemo.dto;

import java.util.Comparator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CountryDTO implements Comparable<CountryDTO> {

    @Schema(example = "Bulgaria")
    private String name;

    @Schema(example = "BG")
    private String countryCode;

    @Override
    public int compareTo(CountryDTO o) {
        return o == null ? 1
                : Comparator.comparing(CountryDTO::getName, Comparator.nullsFirst(String::compareTo))
                    .compare(this, o);
    }
}
