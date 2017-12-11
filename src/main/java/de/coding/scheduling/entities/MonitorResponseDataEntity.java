package de.coding.scheduling.entities;

import de.coding.core.BasicEntity;

import javax.persistence.*;

@Entity
@Table(name = "mon_health_response_data")
public class MonitorResponseDataEntity extends BasicEntity {

    @Column(name = "response_time")
    private Long responseTimeInMillis;

    @Column(name = "response_code")
    private Integer responseCode;

    private MonitorResponseDataEntity() {
        // for hibernate
    }

    public MonitorResponseDataEntity(Long responseTimeInMillis, Integer responseCode) {
        this.responseTimeInMillis = responseTimeInMillis;
        this.responseCode = responseCode;
    }

    public Long getResponseTimeInMillis() {
        return responseTimeInMillis;
    }

    private void setResponseTimeInMillis(Long responseTimeInMillis) {
        this.responseTimeInMillis = responseTimeInMillis;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    private void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
}
