package se.npet.microservices.messageconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("persistence-test")
class MessageConsumerApplicationTests {

  @Test
  void contextLoads() {
  }

}
