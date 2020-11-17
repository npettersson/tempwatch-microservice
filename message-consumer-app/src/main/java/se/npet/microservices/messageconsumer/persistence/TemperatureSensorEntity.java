package se.npet.microservices.messageconsumer.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "temperature_sensors")
public class TemperatureSensorEntity {

  @Id
  @Column(name = "sensor_id")
  private String sensorId;

  @Column(name = "description")
  private String description;

  @Column(name = "temperature_threshold")
  private double temperatureThreshold;

  @Column(name = "allowed_deviation")
  private double allowedDeviation;

  public TemperatureSensorEntity() {
  }

  public String getSensorId() {
    return sensorId;
  }

  public void setSensorId(String sensorId) {
    this.sensorId = sensorId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getTemperatureThreshold() {
    return temperatureThreshold;
  }

  public void setTemperatureThreshold(double temperatureThreshold) {
    this.temperatureThreshold = temperatureThreshold;
  }

  public double getAllowedDeviation() {
    return allowedDeviation;
  }

  public void setAllowedDeviation(double allowedDeviation) {
    this.allowedDeviation = allowedDeviation;
  }
}
