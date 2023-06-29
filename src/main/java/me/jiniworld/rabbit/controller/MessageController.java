package me.jiniworld.rabbit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jiniworld.rabbit.service.Sender;
import me.jiniworld.rabbit.domain.value.MessageValue;
import me.jiniworld.rabbit.domain.response.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/message")
@RequiredArgsConstructor
@RestController
public class MessageController {
  private final Sender sender;

  @PostMapping
  public ResponseEntity<BasicResponse> sendMessage(@RequestBody MessageValue request) {
    sender.send(request);
    log.info("send complete >>>>");
    return ResponseEntity.ok(new BasicResponse(HttpStatus.OK.value(), "send success"));
  }
}
