package se.npet.microservices.messageconsumer.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureReadingsRepository extends JpaRepository<TemperatureReadingEntity, Long> {

  List<TemperatureReadingEntity> findByTemperatureSensorSensorId(String sensorId);
}
