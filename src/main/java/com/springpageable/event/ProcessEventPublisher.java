package com.springpageable.event;

import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.springpageable.dto.ProcessEventDto;
import com.springpageable.model.FutureDevice;

@Component
public class ProcessEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ProcessEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishCustomEvent(FutureDevice futureDevice) {
        ProcessEventDto processEventDto = new ProcessEventDto(UUID.randomUUID());
        var event = new ProcessEvent(futureDevice, processEventDto);
        applicationEventPublisher.publishEvent(event);
    }
}
