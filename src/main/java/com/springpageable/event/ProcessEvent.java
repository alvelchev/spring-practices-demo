package com.springpageable.event;

import org.springframework.context.ApplicationEvent;

import com.springpageable.dto.ProcessEventDto;
import com.springpageable.model.FutureDevice;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ProcessEvent extends ApplicationEvent {

    private final ProcessEventDto processEventDto;

    public ProcessEvent(FutureDevice futureDevice, ProcessEventDto processEventDto) {
        super(futureDevice);
        this.processEventDto = processEventDto;
    }

}
