package se.npet.microservices.messageconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.npet.microservices.messageconsumer.service.model.Deviation;
import se.npet.microservices.messageconsumer.persistence.TemperatureReadingEntity;
import se.npet.microservices.messageconsumer.persistence.TemperatureReadingsRepository;
import se.npet.microservices.messageconsumer.persistence.TemperatureSensorEntity;
import se.npet.microservices.messageconsumer.persistence.TemperatureSensorsRepository;
import se.npet.microservices.messaging.model.TemperatureReadingMessage;

@Service
public class TemperatureReadingsServiceImpl implements TemperatureReadingsService {

  private final static Logger LOGGER = LoggerFactory.getLogger(TemperatureReadingsService.class);

  private final TemperatureReadingsRepository temperatureReadingsRepository;
  private final TemperatureSensorsRepository temperatureSensorsRepository;
  private final TemperatureDeviationCalculator temperatureDeviationCalculator;

  public TemperatureReadingsServiceImpl(
      TemperatureReadingsRepository temperatureReadingsRepository,
      TemperatureSensorsRepository temperatureSensorsRepository,
      TemperatureDeviationCalculator temperatureDeviationCalculator) {
    this.temperatureReadingsRepository = temperatureReadingsRepository;
    this.temperatureSensorsRepository = temperatureSensorsRepository;
    this.temperatureDeviationCalculator = temperatureDeviationCalculator;
  }

  @Override
  public long storeTemperatureReading(TemperatureReadingMessage temperatureReadingMsg) {

    LOGGER.info("Receiving temp reading message <{}>", temperatureReadingMsg.getMessageId());

    String sensorId = temperatureReadingMsg.getSensorId();
    TemperatureSensorEntity temperatureSensor = temperatureSensorsRepository.findById(sensorId)
        .orElseThrow(() -> new RuntimeException("Could not find sensor <" + sensorId + ">"));

    TemperatureReadingEntity temperatureReadingEntity = new TemperatureReadingEntity();
    temperatureReadingEntity.setTemperatureSensor(temperatureSensor);
    temperatureReadingEntity.setTemperature(temperatureReadingMsg.getTemperature());
    temperatureReadingEntity.setMeasurementTs(temperatureReadingMsg.getMeasurementTimeStamp());

    Deviation deviation = temperatureDeviationCalculator.calculateDeviation(temperatureReadingMsg.getTemperature(), temperatureSensor.getTemperatureThreshold(), temperatureSensor.getAllowedDeviation());
    temperatureReadingEntity.setDeviation(deviation);

    TemperatureReadingEntity savedTemperatureReading = temperatureReadingsRepository.save(temperatureReadingEntity);

    LOGGER.info("Stored temp reading message <{}> from sensor <{}>", temperatureReadingMsg.getMessageId(), sensorId);

    return savedTemperatureReading.getId();
  }
}
