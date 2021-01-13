package se.npet.microservices.messaging.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TemperatureReadingMessage {

  private String messageId;
  private String sensorId;

  // temperature value in degrees celsius
  private BigDecimal temperature;

  private LocalDateTime measurementTimeStamp;

  public TemperatureReadingMessage(String messageId, String sensorId, BigDecimal temperature, LocalDateTime measurementTimeStamp) {
    this.messageId = messageId;
    this.sensorId = sensorId;
    this.temperature = temperature;
    this.measurementTimeStamp = measurementTimeStamp;
  }

  public TemperatureReadingMessage() {
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getSensorId() {
    return sensorId;
  }

  public void setSensorId(String sensorId) {
    this.sensorId = sensorId;
  }

  public BigDecimal getTemperature() {
    return temperature;
  }

  public void setTemperature(BigDecimal temperature) {
    this.temperature = temperature;
  }

  public LocalDateTime getMeasurementTimeStamp() {
    return measurementTimeStamp;
  }

  public void setMeasurementTimeStamp(LocalDateTime measurementTimeStamp) {
    this.measurementTimeStamp = measurementTimeStamp;
  }
}
