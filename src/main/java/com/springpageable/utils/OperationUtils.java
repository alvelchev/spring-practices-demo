package com.springpageable.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OperationUtils {

  static List<String> partNumber = Arrays.asList("testRoot1", "testRoot2", "testRoot3");
  static List<String> filteredPartNumber = Arrays.asList("testRoot1");

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

  public static void main(String[] args) {
    var result = getEqualRecordsFromTwoLists(filteredPartNumber);
    System.out.println(result.toString());
  }
}
