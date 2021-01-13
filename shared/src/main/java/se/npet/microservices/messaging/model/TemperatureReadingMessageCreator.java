package se.npet.microservices.messaging.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TemperatureReadingMessageCreator {

  public static TemperatureReadingMessage newMessage(String sensorId, double temperature) {
    return newMessage(sensorId, BigDecimal.valueOf(temperature));
  }

  public static TemperatureReadingMessage newMessage(String sensorId, BigDecimal temperature) {
    return newMessage(sensorId, temperature, LocalDateTime.now());
  }

  public static TemperatureReadingMessage newMessage(String sensorId, BigDecimal temperature, LocalDateTime measurementDateTime) {
    String messageId = UUID.randomUUID().toString();
    return new TemperatureReadingMessage(messageId, sensorId, temperature, measurementDateTime);
  }

}
