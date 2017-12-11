package de.coding.scheduling.services;

import de.coding.scheduling.entities.MonitorResponseDataEntity;
import de.coding.scheduling.repositories.MonitorResponseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthMonitorResponseDataCalculatorImpl implements HealthMonitorResponseDataCalculator {

    private MonitorResponseDataRepository monitorResponseDataRepository;

    @Autowired
    public HealthMonitorResponseDataCalculatorImpl(MonitorResponseDataRepository monitorResponseDataRepository) {
        this.monitorResponseDataRepository = monitorResponseDataRepository;
    }

    @Override
    public long calculateAverage(AverageTypeInterval averageTypeInterval) {
        List<Long> responseTimes = collectResponseTimesForInterval(averageTypeInterval);

        long sum = responseTimes.stream().mapToLong(Long::longValue).sum();

        return sum / responseTimes.size();
    }

    protected List<Long> collectResponseTimesForInterval(AverageTypeInterval averageTypeInterval) {
        final long intervalStart = getIntervalStart(averageTypeInterval);

        return monitorResponseDataRepository.findAll().stream()
                .filter(m -> m.getCreated().after(new Date(intervalStart)))
                .map(MonitorResponseDataEntity::getResponseTimeInMillis).collect(Collectors.toList());
    }

    private long getIntervalStart(AverageTypeInterval averageTypeInterval) {
        final long now = System.currentTimeMillis();
        return now - averageTypeInterval.getTimeInMillis();
    }
}
