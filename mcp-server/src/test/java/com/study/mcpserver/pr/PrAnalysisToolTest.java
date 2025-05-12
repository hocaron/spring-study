package com.study.mcpserver.pr;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("default")
class PrAnalysisToolTest {

    @Autowired
    private PrAnalysisTool prAnalysisTool;

    @Test
    void testAnalyzePr() {
        String title = "회원가입 시 이메일 중복 체크 로직 추가";
        String diff = """
                diff --git a/UserService.java b/UserService.java
                +    public boolean isEmailTaken(String email) {
                +        return userRepository.existsByEmail(email);
                +    }
                +
                +    public void register(User user) {
                +        if (isEmailTaken(user.getEmail())) {
                +            throw new DuplicateEmailException();
                +        }
                +        userRepository.save(user);
                +    }
                """;

        AnalyzeResult result = prAnalysisTool.analyzePr(title, diff);

        log.info("📝 요약: {}", result.summary());
        log.info("📌 고려할 점:");
        result.considerations().forEach(consideration -> log.info("- {}", consideration));
    }
}
