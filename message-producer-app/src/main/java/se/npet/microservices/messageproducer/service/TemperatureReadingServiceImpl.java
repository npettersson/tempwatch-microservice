package se.npet.microservices.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import se.npet.microservices.messaging.Destinations;
import se.npet.microservices.messaging.model.TemperatureReadingMessage;

@Service
public class TemperatureReadingServiceImpl implements TemperatureReadingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureReadingService.class);

  private final JmsTemplate jmsTemplate;

  public TemperatureReadingServiceImpl(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @Override
  public void sendTemperatureReadingMessage(TemperatureReadingMessage temperatureReadingMessage) {
    LOGGER.info("Sending TemperatureReadingMessage <{}>", temperatureReadingMessage.getMessageId());
    jmsTemplate.convertAndSend(Destinations.TEMPERATURE_READINGS_QUEUE, temperatureReadingMessage);
  }

}
