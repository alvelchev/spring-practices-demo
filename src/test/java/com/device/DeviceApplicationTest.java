package com.device;

import com.springpageable.DeviceApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (c) 2023 Robert Bosch GmbH. All rights reserved. Created by VEA3SF on 17.7.2023 г..
 */
@ExtendWith(MockitoExtension.class)
class DeviceApplicationTest {

    @Test
    public void contextLoads() {
        // Test that the application context loads successfully
        DeviceApplication.main(new String[]{});
    }

}