package de.coding.scheduling.services;

import de.coding.scheduling.entities.MonitorResponseDataEntity;
import de.coding.scheduling.repositories.MonitorResponseDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class HealthMonitorResponseDataCalculatorTest {

    @Mock
    private MonitorResponseDataRepository responseDataRepository;

    @InjectMocks
    private HealthMonitorResponseDataCalculatorImpl healthMonitorResponseDataCalculator;

    private List<MonitorResponseDataEntity> responseDataEntities = new ArrayList<>();

    @Before
    public void setUp() {
        createResponseDataForTest();
        doReturn(responseDataEntities).when(responseDataRepository).findAll();
    }

    @Test
    public void testCalculateAverageFor10Seconds() {
        List<Long> responseTimesForInterval = healthMonitorResponseDataCalculator.collectResponseTimesForInterval(
                HealthMonitorResponseDataCalculator.AverageTypeInterval.SECONDS_10);
        assertThat(responseTimesForInterval.size(), is(1));
    }

    @Test
    public void testCalculateAverageFor1Minute() {
        List<Long> responseTimesForInterval = healthMonitorResponseDataCalculator.collectResponseTimesForInterval(
                HealthMonitorResponseDataCalculator.AverageTypeInterval.MINUTE);
        assertThat(responseTimesForInterval.size(), is(2));
    }

    @Test
    public void testCalculateAverageFor1Hour() {
        List<Long> responseTimesForInterval = healthMonitorResponseDataCalculator.collectResponseTimesForInterval(
                HealthMonitorResponseDataCalculator.AverageTypeInterval.HOUR);
        assertThat(responseTimesForInterval.size(), is(3));
    }


    private void createResponseDataForTest() {
        final long now = System.currentTimeMillis();
        MonitorResponseDataEntity responseDataIn10Seconds = new MonitorResponseDataEntity(10L, 200);
        responseDataIn10Seconds.setCreated(new Date(now - 1000 * 5));
        responseDataEntities.add(responseDataIn10Seconds);

        MonitorResponseDataEntity responseDataIn1Minute = new MonitorResponseDataEntity(10L, 200);
        responseDataIn1Minute.setCreated(new Date(now - 1000 * 55));
        responseDataEntities.add(responseDataIn1Minute);

        MonitorResponseDataEntity responseDataIn1Hour = new MonitorResponseDataEntity(10L, 200);
        responseDataIn1Hour.setCreated(new Date(now - 1000 * 60 * 55));
        responseDataEntities.add(responseDataIn1Hour);

    }
}