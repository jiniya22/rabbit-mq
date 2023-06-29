package me.jiniworld.rabbit.service;

import lombok.RequiredArgsConstructor;
import me.jiniworld.rabbit.util.JacksonConverter;
import me.jiniworld.rabbit.domain.enums.MessageUtils;
import me.jiniworld.rabbit.domain.value.MessageValue;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Sender {
  private final RabbitMessagingTemplate rabbitMessagingTemplate;

  @Bean
  public Queue queue() {
    return new Queue(MessageUtils.Q_MESSAGE, false);
  }

  public void send(MessageValue value) {
    String message = JacksonConverter.toJson(value);
    rabbitMessagingTemplate.convertAndSend(MessageUtils.Q_MESSAGE, message);
  }
}
