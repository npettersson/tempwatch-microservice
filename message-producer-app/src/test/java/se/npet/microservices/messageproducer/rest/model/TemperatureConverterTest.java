package se.npet.microservices.messageproducer.rest.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class TemperatureConverterTest {

  @Test()
  public void testWithNull() {
    assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.convert(null, null));

    assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.convert(null, TemperatureScale.C));

    assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.convert(BigDecimal.ONE, null));
  }

  @Test
  public void testConvertFromFahrenheit() {

    assertEquals(BigDecimal.valueOf(-17.78), TemperatureConverter.convert(BigDecimal.ZERO, TemperatureScale.F));

    assertEquals(BigDecimal.valueOf(0, 2), TemperatureConverter.convert(BigDecimal.valueOf(32), TemperatureScale.F));
  }

  @Test
  public void testConvertFromKelvin() {

    assertEquals(BigDecimal.valueOf(6.85), TemperatureConverter.convert(BigDecimal.valueOf(280.00), TemperatureScale.K));

    assertEquals(BigDecimal.valueOf(-2000, 2), TemperatureConverter.convert(BigDecimal.valueOf(253.15), TemperatureScale.K));

  }

}
