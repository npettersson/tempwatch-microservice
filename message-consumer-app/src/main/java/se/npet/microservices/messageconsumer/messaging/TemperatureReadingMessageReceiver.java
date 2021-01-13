package se.npet.microservices.messageconsumer.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import se.npet.microservices.messageconsumer.service.TemperatureReadingsService;
import se.npet.microservices.messaging.Destinations;
import se.npet.microservices.messaging.model.TemperatureReadingMessage;

@Component
public class TemperatureReadingMessageReceiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureReadingMessageReceiver.class);

  private final TemperatureReadingsService temperatureReadingsService;

  public TemperatureReadingMessageReceiver(
      TemperatureReadingsService temperatureReadingsService) {
    this.temperatureReadingsService = temperatureReadingsService;
  }

  @JmsListener(destination = Destinations.TEMPERATURE_READINGS_QUEUE, containerFactory = "tempReadingQueueFactory")
  public void receiveMessage(TemperatureReadingMessage temperatureReadingMessage) {
    LOGGER.info("Receiving temperature reading <{}>", temperatureReadingMessage.getMessageId());
    temperatureReadingsService.storeTemperatureReading(temperatureReadingMessage);
  }
}
