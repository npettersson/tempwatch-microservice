package se.npet.microservices.messageproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MessageProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MessageProducerApplication.class, args);
  }

}
