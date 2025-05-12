package com.study.mcpserver.pr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class PrAnalysisTool {

    private final ChatModel chatModel;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Tool(name = "analyze_pr", description = "Analyze PR to extract tests and improvement points.")
    public List<AnalyzeResult> analyzePr(
        @ToolParam(description = "PR title") String title,
        @ToolParam(description = "PR code diff") String diff
    ) {
        String template = """
            다음 PR의 제목과 코드 diff를 참고하여 테스트한 항목과 추가 고려할 점을 JSON 배열로 정리해줘.
            - 말투는 '~했습니다', '~할 수 있습니다'로 통일
            - 출력은 다음과 같은 형식이어야 해:
            [
              {"type": "test", "content": "입력값 검증 테스트를 수행했습니다."},
              {"type": "consideration", "content": "에러 메시지 포맷을 통일할 수 있습니다."}
            ]

            PR 제목: {title}
            --- BEGIN DIFF ---
            {diff}
            --- END DIFF ---
            """;

        Prompt prompt = new PromptTemplate(template)
            .create(Map.of("title", title, "diff", diff));

        String content = chatModel.call(prompt.getContents());

        try {
            return objectMapper.readValue(content, new TypeReference<List<AnalyzeResult>>() {});
        } catch (Exception e) {
            return List.of(new AnalyzeResult("error", "AI 응답 파싱 실패: " + e.getMessage()));
        }
    }
}
