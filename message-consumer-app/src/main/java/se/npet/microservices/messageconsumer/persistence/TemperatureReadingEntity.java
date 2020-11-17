package se.npet.microservices.messageconsumer.persistence;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import se.npet.microservices.messageconsumer.service.model.Deviation;

@Entity
@Table(name = "temperature_readings")
public class TemperatureReadingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "sensor_id")
  private TemperatureSensorEntity temperatureSensor;

  private double temperature;
  private Deviation deviation;
  private LocalDateTime measurementTs;
  private LocalDateTime receivedTs;

  public TemperatureReadingEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TemperatureSensorEntity getTemperatureSensor() {
    return temperatureSensor;
  }

  public void setTemperatureSensor(TemperatureSensorEntity temperatureSensor) {
    this.temperatureSensor = temperatureSensor;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public Deviation getDeviation() {
    return deviation;
  }

  public void setDeviation(Deviation deviation) {
    this.deviation = deviation;
  }

  public LocalDateTime getMeasurementTs() {
    return measurementTs;
  }

  public void setMeasurementTs(LocalDateTime measurementTs) {
    this.measurementTs = measurementTs;
  }

  public LocalDateTime getReceivedTs() {
    return receivedTs;
  }

  public void setReceivedTs(LocalDateTime receivedTs) {
    this.receivedTs = receivedTs;
  }
}
