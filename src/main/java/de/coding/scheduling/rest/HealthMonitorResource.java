package de.coding.scheduling.rest;

import de.coding.scheduling.entities.MonitorResponseDataEntity;
import de.coding.scheduling.repositories.MonitorResponseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/responseData")
public class HealthMonitorResource {

    private MonitorResponseDataRepository responseDataRepository;

    @Autowired
    public HealthMonitorResource(MonitorResponseDataRepository responseDataRepository) {
        this.responseDataRepository = responseDataRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<MonitorResponseDataEntity> getMonitorResponseDatas() {
        return responseDataRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    MonitorResponseDataEntity getMonitorData(@PathVariable Long id) {
        Optional<MonitorResponseDataEntity> responseDataEntityOptional = responseDataRepository.findById(id);
        return responseDataEntityOptional.orElse(null);
    }

}
