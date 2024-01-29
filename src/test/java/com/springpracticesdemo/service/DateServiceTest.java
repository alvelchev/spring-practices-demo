package com.springpracticesdemo.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springpracticesdemo.dto.DateRequestDTO;

@ExtendWith(MockitoExtension.class)
class DateServiceTest {

    @InjectMocks
    private DateService service;

    @Test
    void demo() {
        DateRequestDTO dateRequestDTO = new DateRequestDTO(LocalDate.now(), LocalDate.now().plusDays(1));
        service.demo(dateRequestDTO);
    }
}