package de.coding.scheduling.services;

import de.coding.scheduling.entities.MonitorResponseDataEntity;
import de.coding.scheduling.repositories.MonitorResponseDataRepository;
import de.coding.scheduling.services.HealthMonitoringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthMonitoringServiceTest {

    @Autowired
    private HealthMonitoringService healthMonitoringService;

    @Autowired
    private MonitorResponseDataRepository monitorResponseDataRepository;

    @Test
    public void testHealthPing() throws IOException {
        assertThat(monitorResponseDataRepository.count(), is(0L));
        healthMonitoringService.healthPing();
        assertThat(monitorResponseDataRepository.count(), is(2L));
    }
}
