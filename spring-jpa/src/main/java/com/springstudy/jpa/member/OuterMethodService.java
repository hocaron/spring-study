package com.springstudy.jpa.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OuterMethodService {

  private final InnerMethodService innerMethodService;

  private static final String DEFAULT_VALUE = "DEFAULT";


  @Transactional
  public String rollBackMethodWithInnerTransaction() {

    String status = DEFAULT_VALUE;
    innerMethodService.innerTransactionMethodWithThrow();

    return status;
  }

  @Transactional
  public String silentlyRollBackedMethodWithTryCatch() {

    String status = DEFAULT_VALUE;

    try {
      status = innerMethodService.innerTransactionMethodWithThrow();
    } catch (RuntimeException e) {
      log.info("예외를 잡았지만, 내부 트랜재션에서 롤백마크 생성");
    }

    return status;
  }

  @Transactional
  public String noRollBackMethod1() {

    String status = DEFAULT_VALUE;

    try {
      status = innerMethodService.innerMethodWithThrow();
    } catch (RuntimeException e) {
    }

    return status;
  }

  @Transactional
  public String noRollBackMethod2() {

    return innerMethodService.innerTransactionMethodWithTryCatch();
  }

  @Transactional
  public String noRollBackMethod3() {

    String status = DEFAULT_VALUE;

    try {
      status = innerMethodService.innerNewTransactionMethodWithThrow();
    } catch (RuntimeException e) {
    }

    return status;
  }
}
