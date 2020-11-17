package se.npet.microservices.messageproducer.rest.model;

import java.time.LocalDateTime;

public class TemperatureReadingDto {

  private String sensorId;
  private double temperature;
  private LocalDateTime measurementTimeStamp;

  public TemperatureReadingDto() {
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
