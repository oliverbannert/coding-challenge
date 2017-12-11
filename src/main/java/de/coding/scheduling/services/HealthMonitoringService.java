package de.coding.scheduling.services;

import java.io.IOException;

public interface HealthMonitoringService {

    void healthPing() throws IOException;
}
