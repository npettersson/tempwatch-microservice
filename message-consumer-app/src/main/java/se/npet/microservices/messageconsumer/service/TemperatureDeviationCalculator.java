package se.npet.microservices.messageconsumer.service;

import java.math.BigDecimal;
import se.npet.microservices.messageconsumer.service.model.Deviation;

public interface TemperatureDeviationCalculator {

  Deviation calculateDeviation(BigDecimal temperatureValue, double threshold, double allowedDeviation);
}
