package se.npet.microservices.messageconsumer.service;

import se.npet.microservices.messageconsumer.service.model.Deviation;

public interface TemperatureDeviationCalculator {

  Deviation calculateDeviation(double temperatureValue, double threshold, double allowedDeviation);
}
