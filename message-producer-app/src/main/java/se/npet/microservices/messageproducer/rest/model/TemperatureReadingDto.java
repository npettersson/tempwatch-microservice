package se.npet.microservices.messageproducer.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TemperatureReadingDto {

  @NotBlank(message = "Sensor id is required")
  private String sensorId;

  @NotNull(message = "Temperature value is required")
  private BigDecimal temperature;

  @NotNull(message = "Temperature scale is required")
  private TemperatureScale scale;

  @NotNull(message = "Reading timestamp is required")
  private LocalDateTime measurementTimeStamp;

  public TemperatureReadingDto() {
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

  public TemperatureScale getScale() {
    return scale;
  }

  public void setScale(TemperatureScale scale) {
    this.scale = scale;
  }

  public LocalDateTime getMeasurementTimeStamp() {
    return measurementTimeStamp;
  }

  public void setMeasurementTimeStamp(LocalDateTime measurementTimeStamp) {
    this.measurementTimeStamp = measurementTimeStamp;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("TemperatureReadingDto{");
    sb.append("sensorId='").append(sensorId).append('\'');
    sb.append(", temperature=").append(temperature);
    sb.append(", scale=").append(scale);
    sb.append(", measurementTimeStamp=").append(measurementTimeStamp);
    sb.append('}');
    return sb.toString();
  }
}
