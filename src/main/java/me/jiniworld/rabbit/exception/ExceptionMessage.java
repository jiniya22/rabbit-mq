package me.jiniworld.rabbit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
  CONVERT_ERROR("변환중 에러가 발생했습니다.");

  private final String message;
}
