package de.coding.scheduling.repositories;

import de.coding.scheduling.entities.MonitorResponseDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MonitorResponseDataRepository extends JpaRepository<MonitorResponseDataEntity, Long> {
}
