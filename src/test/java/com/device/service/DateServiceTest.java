package com.device.service;

import com.springpageable.dto.DateRequestDTO;
import com.springpageable.service.DateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class DateServiceTest {

    @InjectMocks
    private DateService service;

    @Test
    void demo() {
        DateRequestDTO dateRequestDTO =
                new DateRequestDTO(LocalDate.now(), LocalDate.now().plusDays(1));
        service.demo(dateRequestDTO);
    }
}