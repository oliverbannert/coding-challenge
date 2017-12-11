package de.coding.scheduling.services;

import de.coding.scheduling.HealthMonitorProperties;
import de.coding.scheduling.entities.MonitorResponseDataEntity;
import de.coding.scheduling.repositories.MonitorResponseDataRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class HealthMonitoringServiceImpl implements HealthMonitoringService {

    private static final Logger LOGGER = Logger.getLogger(HealthMonitoringServiceImpl.class.getName());

    @Value(HealthMonitorProperties.MONITOR_URL)
    private String healthMonitorUrl;

    private MonitorResponseDataRepository monitorResponseDataRepository;

    @Autowired
    public HealthMonitoringServiceImpl(MonitorResponseDataRepository monitorResponseDataRepository) {
        this.monitorResponseDataRepository = monitorResponseDataRepository;
    }

    @Override
    public void healthPing() throws IOException {
        LOGGER.info("start health-ping");
        final long startTime = System.currentTimeMillis();

        HttpResponse response = Request.Get(healthMonitorUrl).execute().returnResponse();

        final long responseTime = System.currentTimeMillis() - startTime;
        final int statusCode = response.getStatusLine().getStatusCode();

        monitorResponseDataRepository.save(new MonitorResponseDataEntity(responseTime, statusCode));
    }
}
