package se.npet.microservices.messaging.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class TemperatureReadingMessageCreator {

  public static TemperatureReadingMessage newMessage(String sensorId, double temperature) {
    return newMessage(sensorId, temperature, LocalDateTime.now());
  }

  public static TemperatureReadingMessage newMessage(String sensorId, double temperature, LocalDateTime measurementDateTime) {
    String messageId = UUID.randomUUID().toString();
    return new TemperatureReadingMessage(messageId, sensorId, temperature, measurementDateTime);
  }

}
