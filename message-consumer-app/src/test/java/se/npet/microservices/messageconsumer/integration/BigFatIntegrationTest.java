package se.npet.microservices.messageconsumer.integration;

import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.jdbc.Sql;
import se.npet.microservices.messageconsumer.persistence.TemperatureReadingsRepository;
import se.npet.microservices.messaging.Destinations;
import se.npet.microservices.messaging.model.TemperatureReadingMessage;
import se.npet.microservices.messaging.model.TemperatureReadingMessageCreator;
import se.npet.microservices.testing.AbstractDbAndMessagingIntegrationTest;

@SpringBootTest
@Sql("/test-data/insert_temp_sensors.sql")
public class BigFatIntegrationTest extends AbstractDbAndMessagingIntegrationTest {

  private static final String SENSOR_ID = "sensor_out";

  @Autowired
  private JmsTemplate jmsTemplate;

  @Autowired
  private TemperatureReadingsRepository temperatureReadingsRepository;

  @Test
  public void testPublishTempReadingAndCheckIfItIsStored() {

    TemperatureReadingMessage tempReadingMsg = TemperatureReadingMessageCreator.newMessage(SENSOR_ID, 18.0);

    jmsTemplate.convertAndSend(Destinations.TEMPERATURE_READINGS_QUEUE, tempReadingMsg);

    await().atMost(2, TimeUnit.SECONDS)
        .until(() -> !temperatureReadingsRepository.findByTemperatureSensorSensorId(SENSOR_ID).isEmpty());
  }
}
