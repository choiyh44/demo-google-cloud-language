package com.example.demogooglecloudlanguage.common.util;

import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Sentence;
import com.google.cloud.language.v1.Token;
import com.google.cloud.language.v2.AnalyzeSentimentResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class GoogleCloudLanguageUtilTest {
    @Test
    void analyzeSyntax() throws IOException {
        String inputPath = "src/main/resources/sample.txt";
        String text = readString(Path.of(inputPath));

        AnalyzeSyntaxResponse result = GoogleCloudLanguageUtil.analyzeSyntax(text);

        assertNotNull(result);
        assertFalse(result.getSentencesList().isEmpty());
        assertFalse(result.getTokensList().isEmpty());

        List<Sentence> sentencesList = result.getSentencesList().stream().filter(sentence -> sentence.getText().getContent().contains("예금자") && sentence.getText().getContent().contains("보호")).toList();

        log.info(sentencesList.toString());
        for (Sentence sentence: sentencesList) {
            log.info("Content: {}, Begin offset: {}, length: {}", sentence.getText().getContent(), sentence.getText().getBeginOffset(), sentence.getText().getContent().length());
            analyzeSyntaxSub(sentence.getText().getContent());
//            List<Token> tokenList = result.getTokensList().stream().filter(token -> token.getText().getBeginOffset() >= beginIndex && token.getText().getBeginOffset() < endIndex).toList();
//            for (Token token: tokenList) {
//                log.info("Text: {}, begin offset: {}", token.getLemma(), token.getText().getBeginOffset());
//            }
        }

    }

    // for API analysis
    private void analyzeSyntaxSub(String text) throws IOException {
        AnalyzeSyntaxResponse result = GoogleCloudLanguageUtil.analyzeSyntax(text);
        log.info("text: {}", text);
        List<Token> tokenList = result.getTokensList();
        for (int i = 0; i < tokenList.size(); i++) {
            Token token = tokenList.get(i);
            log.info("Text: {}, tag: {}, tag value: {}, begin offset: {}, index: {}, head token index: {}", token.getLemma(), token.getText().getBeginOffset(), token.getPartOfSpeech().getTag(), token.getPartOfSpeech().getTagValue(), i+1, token.getDependencyEdge().getHeadTokenIndex());
        }
    }

    @Test
    void analyzeSyntax_2() throws IOException {
        String text = "이 금융상품은 예금자보호법에 따라 보호되지 않습니다.";
        analyzeSyntaxSub(text);
    }

    @Test
    void analyzeSentiment() throws IOException {
        String text1 = "이 금융상품은 예금자보호법에 따라 보호되지 않습니다.";
        String text2 = "이 금융상품은 예금자보호법에 따라 보호됩니다.";
        AnalyzeSentimentResponse result;

        // text1 테스트
        result = GoogleCloudLanguageUtil.analyzeSentiment(text1);
        assertNotNull(result);
        log.info("Score: {}, Magnitude: {}", result.getDocumentSentiment().getScore(), result.getDocumentSentiment().getMagnitude());

        // text2 테스트
        result = GoogleCloudLanguageUtil.analyzeSentiment(text2);
        assertNotNull(result);
        log.info("Score: {}, Magnitude: {}", result.getDocumentSentiment().getScore(), result.getDocumentSentiment().getMagnitude());

    }

}
