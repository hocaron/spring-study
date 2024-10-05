package com.springstudy.jpa.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class RollbackMarkTest {

  @Autowired
  OuterMethodService outerMethodService;

  @Test
  @DisplayName("내부 트랜잭션에서 예외 발생하여 전체 롤백")
  void rollBackMethodWithInnerTransaction() {
    assertThatThrownBy(() -> outerMethodService.rollBackMethodWithInnerTransaction())
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  @DisplayName("try catch 로 롤백이 안 되기를 바라지만 내부 트랜잭션 롤백마크로 인해 롤백되는 테스트")
  void silentlyRollBackedMethodTest() {
    outerMethodService.silentlyRollBackedMethodWithTryCatch();
    assertThatThrownBy(() -> outerMethodService.silentlyRollBackedMethodWithTryCatch())
        .isInstanceOf(UnexpectedRollbackException.class);
  }

  @Test
  @DisplayName("내부 메서드에서 트랜잭션을 제거하여 롤백이 되지 않도록 처리한 테스트")
  void noRollBackMethod1Test() {

    assertThat(outerMethodService.noRollBackMethod1()).isEqualTo("DEFAULT");
  }

  @Test
  @DisplayName("내부 메서드에서 트랜잭션이 있지만, 외부로 에러를 던지지 않도록 처리하여 롤백이 되지 않도록 처리한 테스트")
  void noRollBackMethod2Test() {

    assertThat(outerMethodService.noRollBackMethod2()).isEqualTo("DEFAULT");
  }

  @Test
  @DisplayName("내부 메서드에서 트랜잭션이 있지만, 새로운 트랜잭션으로 처리하여 롤백이 되지 않도록 처리한 테스트")
  void noRollBackMethod3Test() {

    assertThat(outerMethodService.noRollBackMethod3()).isEqualTo("DEFAULT");
  }
}
