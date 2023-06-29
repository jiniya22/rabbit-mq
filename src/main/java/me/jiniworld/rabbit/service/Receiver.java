package me.jiniworld.rabbit.service;

import lombok.extern.slf4j.Slf4j;
import me.jiniworld.rabbit.domain.enums.MessageUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {
  @RabbitListener(id = MessageUtils.Q_MESSAGE, queues = MessageUtils.Q_MESSAGE)
  public void handle(String request) {
    log.info("<<< receive data: {} ", request);
  }
}
