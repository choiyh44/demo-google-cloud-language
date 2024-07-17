package com.example.demogooglecloudlanguage.common.util;

import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Token;
import com.google.cloud.language.v2.AnalyzeSentimentRequest;
import com.google.cloud.language.v2.AnalyzeSentimentResponse;

import java.io.IOException;
import java.util.List;

public class GoogleCloudLanguageUtil {

    public static AnalyzeSyntaxResponse analyzeSyntax(String text) throws IOException {
        // Instantiate the Language client com.google.cloud.language.v1.LanguageServiceClient
        try (com.google.cloud.language.v1.LanguageServiceClient language =
                     com.google.cloud.language.v1.LanguageServiceClient.create()) {
            com.google.cloud.language.v1.Document doc =
                    com.google.cloud.language.v1.Document.newBuilder().setContent(text)
                            .setType(com.google.cloud.language.v1.Document.Type.PLAIN_TEXT).build();
            AnalyzeSyntaxRequest request =
                    AnalyzeSyntaxRequest.newBuilder()
                            .setDocument(doc)
                            .setEncodingType(com.google.cloud.language.v1.EncodingType.UTF16)
                            .build();
            // Analyze the syntax in the given text
            AnalyzeSyntaxResponse response = language.analyzeSyntax(request);
            // Print the response
//            for (Token token : response.getTokensList()) {
//                System.out.printf("\tText: %s\n", token.getText().getContent());
//                System.out.printf("\tBeginOffset: %d\n", token.getText().getBeginOffset());
//                System.out.printf("Lemma: %s\n", token.getLemma());
//                System.out.printf("PartOfSpeechTag: %s\n", token.getPartOfSpeech().getTag());
//                System.out.printf("\tAspect: %s\n", token.getPartOfSpeech().getAspect());
//                System.out.printf("\tCase: %s\n", token.getPartOfSpeech().getCase());
//                System.out.printf("\tForm: %s\n", token.getPartOfSpeech().getForm());
//                System.out.printf("\tGender: %s\n", token.getPartOfSpeech().getGender());
//                System.out.printf("\tMood: %s\n", token.getPartOfSpeech().getMood());
//                System.out.printf("\tNumber: %s\n", token.getPartOfSpeech().getNumber());
//                System.out.printf("\tPerson: %s\n", token.getPartOfSpeech().getPerson());
//                System.out.printf("\tProper: %s\n", token.getPartOfSpeech().getProper());
//                System.out.printf("\tReciprocity: %s\n", token.getPartOfSpeech().getReciprocity());
//                System.out.printf("\tTense: %s\n", token.getPartOfSpeech().getTense());
//                System.out.printf("\tVoice: %s\n", token.getPartOfSpeech().getVoice());
//                System.out.println("DependencyEdge");
//                System.out.printf("\tHeadTokenIndex: %d\n", token.getDependencyEdge().getHeadTokenIndex());
//                System.out.printf("\tLabel: %s\n\n", token.getDependencyEdge().getLabel());
//            }
            return response;
        }
    }

    public static AnalyzeSentimentResponse analyzeSentiment(String text) throws IOException {
        // Instantiate the Language client com.google.cloud.language.v1.LanguageServiceClient
        try (com.google.cloud.language.v2.LanguageServiceClient language =
                     com.google.cloud.language.v2.LanguageServiceClient.create()) {
            com.google.cloud.language.v2.Document doc =
                    com.google.cloud.language.v2.Document.newBuilder().setContent(text)
                            .setType(com.google.cloud.language.v2.Document.Type.PLAIN_TEXT).build();
            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
            // Analyze the syntax in the given text
            return response;
        }
    }

}