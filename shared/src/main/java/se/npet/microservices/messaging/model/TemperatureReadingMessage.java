package se.npet.microservices.messaging.model;

import java.time.LocalDateTime;

public class TemperatureReadingMessage {

  private String messageId;
  private String sensorId;
  private double temperature;

  private LocalDateTime measurementTimeStamp;

  public TemperatureReadingMessage(String messageId, String sensorId, double temperature, LocalDateTime measurementTimeStamp) {
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

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public LocalDateTime getMeasurementTimeStamp() {
    return measurementTimeStamp;
  }

  public void setMeasurementTimeStamp(LocalDateTime measurementTimeStamp) {
    this.measurementTimeStamp = measurementTimeStamp;
  }
}
