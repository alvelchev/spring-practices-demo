package com.springpracticesdemo.event;

import static com.springpracticesdemo.mock.Constants.FIXTURE_FUTURE_DEVICE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import com.springpracticesdemo.model.FutureDevice;

import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;

@ExtendWith(MockitoExtension.class)
class ProcessEventPublisherTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Fixture(FIXTURE_FUTURE_DEVICE)
    private List<FutureDevice> futureDeviceList;

    @InjectMocks
    private ProcessEventPublisher eventPublisher;

    @BeforeEach
    void setUp() throws Exception {
        FixtureAnnotations.initFixtures(this);
    }

    @Test
    void publishCustomEventTest() {
        // Act
        eventPublisher.publishCustomEvent(futureDeviceList.get(0));

        // Assert
        verify(applicationEventPublisher, times(1)).publishEvent(any(ProcessEvent.class));
    }
}