package de.coding.scheduling.rest;

import de.coding.scheduling.entities.MonitorResponseDataEntity;
import de.coding.scheduling.repositories.MonitorResponseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
public class HealthMonitorResource {

    private MonitorResponseDataRepository responseDataRepository;

    @Autowired
    public HealthMonitorResource(MonitorResponseDataRepository responseDataRepository) {
        this.responseDataRepository = responseDataRepository;
    }

    @RequestMapping(value = "/responseData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<MonitorResponseDataEntity> getMonitorResponseDatas() {
        return responseDataRepository.findAll();
    }

}
