package com.springpageable.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class OperationUtils {

  static List<String> partNumber = Arrays.asList("testRoot1", "testRoot2", "testRoot3");

  private OperationUtils() {}
  /**
   * Find and filter equals records from two lists
   *
   * @param filteredPartNumber - list of members which first list to be filtered
   * @return {@link List} of strings which are the same
   */
  public static List<String> getEqualRecordsFromTwoLists(List<String> filteredPartNumber) {
    return partNumber.stream()
        .filter(
            country ->
                filteredPartNumber.stream()
                    .anyMatch(countryCode -> countryCode.equalsIgnoreCase(country)))
        .collect(Collectors.toList());
  }
}
