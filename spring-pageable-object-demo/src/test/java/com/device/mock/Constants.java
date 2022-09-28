package com.device.mock;

public final class Constants {

    // Authentication
    public static final String TEST_422_USERNAME = "snowjous";

    public static final String SUBJECT_CLAIM = "sub";
    public static final String FIRST_NAME_CLAIM = "given_name";
    public static final String LAST_NAME_CLAIM = "family_name";
    public static final String COUNTRY_CODE_CLAIM = "countryCode";
    public static final String ROLES_CLAIM = "memberOf";

    public static final String ACCESS_TOKEN_TEST_FIRST_NAME = "testFirstName";
    public static final String ACCESS_TOKEN_TEST_LAST_NAME = "testLastName";
    public static final String ACCESS_TOKEN_COUNTRY_CODE = "BG";
    public static final String ACCESS_TOKEN_VALUE = "testToken";
    public static final String BEARER_HEADER_VALUE = "Bearer " + ACCESS_TOKEN_VALUE;

    public static final String[] DEFAULT_USER_ROLES = {"CN=LDE08_IOMT_SD_Administrators_QAS,OU=Extranet,OU=Groups,O=BBraun",
            "CN=LDE08_IOMT_SD_Administrators_DEV,OU=Extranet,OU=Groups,O=BBraun",
            "CN=LDE08_IOMT_SD_PortalUsers,OU=Extranet,OU=Groups,O=BBraun",
            "CN=HC_Space_Station_tecService,OU=Extranet,OU=Groups,O=BBraun",
            "CN=Extranet_DE_XX_ServicePortal,OU=Extranet,OU=Groups,O=BBraun",
            "CN=ITDevTools_CI_Internal_Developers,OU=Extranet,OU=Groups,O=BBraun"};

    // Countries
    public static final String TEST_COUNTRY_NAME = "Germany";

    // Customers
    public static final String TEST_CUSTOMER_NAME = "testCustomer";
    public static final Long TEST_CUSTOMER_ID = 15L;

    public static final String TEST_CUSTOMER_SAP_NUMBER = "testCustomerSapNumber";

    public static final String CUSTOMER_ID_PATH_VARIABLE = "{customerId}";

    // Fixtures
    public static final String LIST_OF_GET_USER_RESPONSE_DTO = "getUserResponseDTOs";
    public static final String LIST_OF_USERS = "users";
    public static final String CUSTOMERS = "customers";
    public static final String CUSTOMER_REQUEST_DTO = "customerRequestDTO";
    public static final String GET_CUSTOMER_RESPONSE_DTOS = "getCustomerResponseDTOs";
    public static final String CUSTOMER_RESPONSE_ID_DTO = "customerResponseIdDTO";
    public static final String GET_CUSTOMER_RESPONSE_V2_DTO_LIST = "getCustomerResponseV2DTOList";
    public static final String GET_CUSTOMER_RESPONSE_V5_DTO_LIST = "getCustomerResponseV5DTOList";
    public static final String CUSTOMER_REQUEST_DTO_WITH_INDIVIDUAL_CONTACTS = "customerRequestDTOWithInvalidContacts";

    //Other
    public static final String TEST_PARAM = "test";
    public static final String TEST_CONTENT = "content";
    public static final String TEST_SEARCH_PARAMETER = "searchParameter";
    public static final String TEST_FAMILY = "family";
    public static final String TEST_TYPE = "type";
    public static final String TEST_FAILURE_MESSAGE = "Expected to fail";
    public static final String MISSING_SUPPLIER_IMPLEMENTATION = "Missing Supplier implementation";
    public static final int TEST_CE_REGION_TYPE = 1;
    public static final int TEST_FRENCH_OVERSEAS_REGION_TYPE = 2;
    public static final int TEST_NON_SUPPORTED_REGION_TYPE = 3;
    public static final String FIXTURE_FUTURE_DEVICE_DTO = "futureDeviceDTO";
    public static final String FIXTURE_FUTURE_DEVICE = "futureDevice";
    public static final String FIXTURE_GET_FUTURE_DEVICE_RESPONSE_DTO = "getFutureDeviceResponseDTO";

    public static final String COUNTRY_BG_KEY = "BG";
    public static final String COUNTRY_BG_VALUE = "Bulgaria";
    public static final String COUNTRY_DE_KEY = "DE";
    public static final String COUNTRY_DE_VALUE = "Germany";
    public static final String NON_EXISTING_COUNTRY_CODE = "Non Existing";
    public static final int ALL_COUNTRIES_SIZE = 243;
    public static final String EMPTY_STRING = "";

    private Constants() {}
}
