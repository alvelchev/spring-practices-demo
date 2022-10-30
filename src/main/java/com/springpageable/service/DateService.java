package com.springpageable.service;

import com.springpageable.dto.DateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DateService {

  public void demo(DateRequestDTO dateRequestDTO) {
    log.info(dateRequestDTO.toString());
  }
}
