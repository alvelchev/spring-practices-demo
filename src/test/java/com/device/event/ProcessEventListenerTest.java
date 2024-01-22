package com.device.event;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springpageable.dto.ProcessEventDto;
import com.springpageable.event.ProcessEvent;
import com.springpageable.event.ProcessEventListener;

@ExtendWith(MockitoExtension.class)
class ProcessEventListenerTest {

    @Mock
    private ProcessEvent event;

    @InjectMocks
    private ProcessEventListener eventListener;

    @Test
    void onApplicationEventTest() {
        // Arrange
        ProcessEventDto processEventDto = new ProcessEventDto(UUID.randomUUID());
        when(event.getProcessEventDto()).thenReturn(processEventDto);

        // Act
        eventListener.onApplicationEvent(event);

        // Assert
        verify(event, times(1)).getProcessEventDto();
    }
}
