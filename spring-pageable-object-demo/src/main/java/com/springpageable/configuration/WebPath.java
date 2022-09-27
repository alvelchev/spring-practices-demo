package com.springpageable.configuration;

public final class WebPath {

  public static final String API_VERSION_1 = "/v1";
  public static final String API_VERSION_2 = "/v2";

  public static final String PATH_DEVICE = "/devices/{uuid}";
  public static final String PATH_DEVICE_UUID = "/device-uuid";
  public static final String PATH_DEVICES = "/devices";
  public static final String PATH_FUTURE_DEVICES = "/futures";
  public static final String PATH_REMOVE_FUTURE_DEVICE = "/device/futures/{id}";
  public static final String PATH_DEVICE_REFERENCES = "/devices/{uuid}/references";
  public static final String PATH_DEVICE_REFERENCE = "/devices/{uuid}/references/{id}";
  public static final String PATH_DEVICE_CUSTOMER = "/devices/{uuid}/customer";
  public static final String PATH_DEVICE_POSITION = "/devices/{uuid}/position";
  public static final String PATH_CUSTOMER_DEVICES = "/customers/{customerId}/devices";
  public static final String PATH_CUSTOMER_APPLICATIONS = "/customers/{customerId}/applications";
  public static final String PATH_CUSTOMERS_AUTOMATIC_DISTRIBUTION =
      "/customers/automatic-distribution";
  public static final String PATH_CUSTOMERS_LOCATION = "/customers/location";
  public static final String PATH_CUSTOMER = "/customers/{customerId}";
  public static final String PATH_CONNECTORS = "/connectors";
  public static final String PATH_CONNECTOR = "/connectors/{uuid}";

  private WebPath() {}
}
