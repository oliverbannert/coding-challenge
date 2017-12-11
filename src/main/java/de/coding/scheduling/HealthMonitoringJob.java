package de.coding.scheduling;

import de.coding.scheduling.services.HealthMonitoringServiceImpl;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@DisallowConcurrentExecution
public class HealthMonitoringJob implements Job {

    private final static Logger LOGGER = Logger.getLogger(HealthMonitoringJob.class.getName());

    @Autowired
    private HealthMonitoringServiceImpl healthMonitoringService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            healthMonitoringService.healthPing();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "A Problem occurred while ping for health. \n {0}", e);
        }
    }
}
