package se.npet.microservices.messageproducer.rest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TemperatureConverter {

  public static BigDecimal convert(BigDecimal tempValue, TemperatureScale scale) {

    if (tempValue == null) {
      throw new IllegalArgumentException("tempValue can not be null");
    } else if (scale == null) {
      throw new IllegalArgumentException("scale can not be null");
    }

    switch (scale) {
      case C:
        return tempValue;
      case F:
        return fromFahrenheit(tempValue);
      case K:
        return fromKelvin(tempValue);
      default:
        throw new IllegalStateException("Unexpected scale: " + scale);
    }
  }

  private static BigDecimal fromFahrenheit(BigDecimal tempValue) {
    return tempValue.subtract(BigDecimal.valueOf(32)).divide(BigDecimal.valueOf(1.8), 2, RoundingMode.HALF_UP);
  }

  private static BigDecimal fromKelvin(BigDecimal tempValue) {
    return tempValue.subtract(BigDecimal.valueOf(273.15));
  }
}
