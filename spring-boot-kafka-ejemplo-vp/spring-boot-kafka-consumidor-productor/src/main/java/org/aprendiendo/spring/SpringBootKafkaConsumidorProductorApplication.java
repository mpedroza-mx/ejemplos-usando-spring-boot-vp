package org.aprendiendo.spring;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class SpringBootKafkaConsumidorProductorApplication {
  private static final Logger logger = LoggerFactory.getLogger(SpringBootKafkaConsumidorProductorApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SpringBootKafkaConsumidorProductorApplication.class, args);
  }


  @KafkaListener(id = "applicacion-spring-boot",
      topics = "spring-boot-kafka-consumidor-productor")
  public void consumirMensajes(String mensaje) {
    logger.info("********************************************Mensaje Recibido***************************************" );
    logger.info(mensaje);
  }

  @Bean
  public NewTopic crearTopic() {
    return new NewTopic("spring-boot-kafka-consumidor-productor", 1, (short) 1);
  }

}
