package de.coding.scheduling.controller;

import de.coding.scheduling.services.HealthMonitorResponseDataCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HealthMonitorController {

    private HealthMonitorResponseDataCalculator healthMonitorResponseDataCalculator;

    @Autowired
    public HealthMonitorController(HealthMonitorResponseDataCalculator healthMonitorResponseDataCalculator) {
        this.healthMonitorResponseDataCalculator = healthMonitorResponseDataCalculator;
    }

    @RequestMapping(value = "/averageResponseData", method = RequestMethod.GET)
    public String getResponseData(Model model) {
        model.addAttribute("averageIn10Seconds", healthMonitorResponseDataCalculator
                .calculateAverage(HealthMonitorResponseDataCalculator.AverageTypeInterval.SECONDS_10));
        model.addAttribute("averageIn1Minute", healthMonitorResponseDataCalculator
                .calculateAverage(HealthMonitorResponseDataCalculator.AverageTypeInterval.MINUTE));
        model.addAttribute("averageIn1Hour", healthMonitorResponseDataCalculator
                .calculateAverage(HealthMonitorResponseDataCalculator.AverageTypeInterval.HOUR));
        return "responseData";
    }
}
