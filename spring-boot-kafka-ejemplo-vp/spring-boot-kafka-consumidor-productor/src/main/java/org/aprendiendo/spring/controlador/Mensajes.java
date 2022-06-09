package org.aprendiendo.spring.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Mensajes {
  private static final Logger logger = LoggerFactory.getLogger(Mensajes.class);
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  String kafkaTopic = "spring-boot-kafka-consumidor-productor";

  @PostMapping(value = "/mensajes", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String producirMensaje(@RequestBody Map<String,String> message) {
    ListenableFuture<SendResult<String,String>> respuesta = kafkaTemplate.send(kafkaTopic, message.get("mensaje"));
    respuesta.addCallback(exitosa -> {
      logger.info("Respuesta exitosa de kafka");
      logger.info("Offset actual: " +  exitosa.getRecordMetadata().offset());
      logger.info("Topic actual: " +  exitosa.getRecordMetadata().topic());
      logger.info("Partition actual: " +  exitosa.getRecordMetadata().partition());
    },(KafkaFailureCallback<String, String>) fallida -> {
      logger.info("Respuesta fallida kafka");
      logger.info("Error: " +  fallida.getMessage());
    });
    return "Mensaje enviado a la siguiente topic: spring-boot-kafka-consumidor-productor  ";
  }
}
