package ua.ldv.telegrambotspring;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class OpenAIService {
    private final OpenAiConfig openAiConfig;

/*    public List<String> getCompletion(String prompt) {

        OpenAiService service = new OpenAiService(openAiConfig.getToken(), Duration.ofSeconds(50));
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model("gpt-3.5-turbo")
                .temperature(0.9)
                .user("telegramBot")
                .build();
        List<String> choices = new ArrayList<>();

        for (CompletionChoice choice : service.createCompletion(completionRequest).getChoices()) {
            choices.add(choice.getText());
        }
        return choices;
    }*/

    public List<String> getChatCompletion(List<Message> messages) {
        ChatMessage chatMessage = new ChatMessage("assistant", messages.toString());
        OpenAiService service = new OpenAiService(openAiConfig.getToken(), Duration.ofSeconds(50));
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(Collections.singletonList(chatMessage))
                .model("gpt-3.5-turbo")
                .temperature(0.9)
                .build();
        List<String> choices = new ArrayList<>();
        for (ChatCompletionChoice choice : service.createChatCompletion(chatCompletionRequest).getChoices()) {
            choices.add(choice.getMessage().getContent());
        }
        return choices;
    }
}