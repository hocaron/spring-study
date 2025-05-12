package com.study.mcpserver.pr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class PrAnalysisTool {

    private final ChatModel chatModel;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Tool(name = "analyze_pr", description = "Analyze PR to extract tests and improvement points.")
    public AnalyzeResult analyzePr(
        @ToolParam(description = "PR title") String title,
        @ToolParam(description = "PR code diff") String diff
    ) {
        String prompt = """
            아래는 GitHub Pull Request에 대한 정보입니다.
            PR의 변경 내용을 바탕으로 다음 두 가지를 수행해주세요:

            1. PR의 변경 목적과 핵심을 한 문단으로 요약해주세요.
            2. 개선하거나 추가로 고려할 수 있는 포인트를 3가지 JSON 배열로 정리해주세요.

            다음과 같은 형식으로 출력해주세요:

            {
              "summary": "이 PR은 ~~을 목적으로 A 기능에 B 로직을 추가한 작업입니다.",
              "considerations": [
                "예외 케이스에 대한 처리 분기가 부족할 수 있습니다.",
                "메서드명이 명확하지 않아 역할 파악이 어렵습니다.",
                "테스트 커버리지가 부족합니다."
              ]
            }

            PR 제목: %s
            --- BEGIN DIFF ---
            %s
            --- END DIFF ---
            """.formatted(title, diff);

        String content = chatModel.call(prompt);

        try {
            return objectMapper.readValue(content, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("AI 응답 파싱 실패: {}" + e.getMessage(), content);
            throw new IllegalArgumentException();
        }
    }
}
