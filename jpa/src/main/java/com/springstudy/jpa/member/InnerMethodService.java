package com.springstudy.jpa.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InnerMethodService {

  private static final String DEFAULT_VALUE = "DEFAULT";
  private final MemberRepository memberRepository;

  @Transactional
  public String innerTransactionMethodWithThrow() {

    throw new RuntimeException();
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public String innerNewTransactionMethodWithThrow() {

    throw new RuntimeException();
  }

  public String innerMethodWithThrow() {

    throw new RuntimeException();
  }

  @Transactional
  public String innerTransactionMethodWithTryCatch() {

    String status = DEFAULT_VALUE;
    try {
      throwMethod();
    } catch (Exception e) {
    }

    return status;
  }

  private void throwMethod() {

    throw new RuntimeException();
  }

  @Async
  @Transactional
  public void test() {
    memberRepository.findById(1L).orElseThrow();
    try {
      Thread.sleep(12_000);
    } catch (Exception e) {
    }
  }
}
