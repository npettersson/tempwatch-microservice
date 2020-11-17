package se.npet.microservices.messageconsumer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureSensorsRepository extends JpaRepository<TemperatureSensorEntity, String> {

}
