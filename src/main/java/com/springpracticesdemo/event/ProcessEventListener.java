package com.springpracticesdemo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProcessEventListener implements ApplicationListener<ProcessEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEventListener.class);

    @Override
    public void onApplicationEvent(ProcessEvent event) {
        LOGGER.info("Received spring custom event - {}", event.getProcessEventDto());
    }
}
