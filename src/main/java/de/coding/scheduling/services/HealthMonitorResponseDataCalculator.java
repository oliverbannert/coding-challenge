package de.coding.scheduling.services;


import java.util.Date;

public interface HealthMonitorResponseDataCalculator {

    enum AverageTypeInterval {
        SECONDS_10(1000 * 10), MINUTE(1000 * 60), HOUR(1000 * 60 * 60);

        AverageTypeInterval(long timeInMillis) {
            this.timeInMillis = timeInMillis;
        }

        private long timeInMillis;

        public long getTimeInMillis() {
            return timeInMillis;
        }
    }

    long calculateAverage(AverageTypeInterval averageTypeInterval);
}
