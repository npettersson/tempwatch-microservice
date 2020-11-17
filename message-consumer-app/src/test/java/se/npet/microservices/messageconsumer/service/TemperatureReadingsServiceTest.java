package se.npet.microservices.messageconsumer.service;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import se.npet.microservices.messageconsumer.persistence.TemperatureReadingEntity;
import se.npet.microservices.messageconsumer.persistence.TemperatureReadingsRepository;
import se.npet.microservices.messaging.model.TemperatureReadingMessage;
import se.npet.microservices.messaging.model.TemperatureReadingMessageCreator;

@SpringBootTest
@ActiveProfiles("persistence-test")
@Sql("/test-data/insert_temp_sensors.sql")
public class TemperatureReadingsServiceTest {

  @Autowired
  private TemperatureReadingsRepository temperatureReadingsRepository;
  @Autowired
  private TemperatureReadingsService temperatureReadingsService;

  @Test
  public void testSaveReading() {

    TemperatureReadingMessage tempReadingMsg = TemperatureReadingMessageCreator.newMessage("sensor_out", 18.0);
    long temperatureReadingId = temperatureReadingsService.storeTemperatureReading(tempReadingMsg);

    TemperatureReadingEntity persistedReading = temperatureReadingsRepository.getOne(temperatureReadingId);

    assertNotNull(persistedReading);
  }

}
