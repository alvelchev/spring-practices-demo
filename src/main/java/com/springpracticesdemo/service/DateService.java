package com.springpracticesdemo.service;

import org.springframework.stereotype.Service;

import com.springpracticesdemo.dto.DateRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DateService {

    public void demo(DateRequestDTO dateRequestDTO) {
        log.info(dateRequestDTO.toString());
    }
}
