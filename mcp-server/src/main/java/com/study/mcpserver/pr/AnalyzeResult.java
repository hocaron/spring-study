package com.study.mcpserver.pr;


import java.util.List;

public record AnalyzeResult(
    String summary,
    List<String> considerations
) {
}
