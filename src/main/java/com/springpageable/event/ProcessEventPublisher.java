package com.springpageable.event;

import com.springpageable.dto.ProcessEventDto;
import com.springpageable.model.FutureDevice;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProcessEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public ProcessEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishCustomEvent(FutureDevice futureDevice) {
        ProcessEventDto processEventDto = new ProcessEventDto(UUID.randomUUID());
        var event = new ProcessEvent(futureDevice, processEventDto);
        applicationEventPublisher.publishEvent(event);
    }
}
