package com.springpageable;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (c) 2023 Robert Bosch GmbH. All rights reserved. Created by VEA3SF on 17.7.2023 г..
 */
@SpringBootTest
class DeviceApplicationTest {

    @Test
    public void mainTest() {
        // Test that the main method runs without any exceptions
        assertDoesNotThrow(() -> DeviceApplication.main(new String[]{}));
    }
}