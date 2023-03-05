package ua.ldv.telegrambotspring;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class OpenAIService {
    private final OpenAiConfig openAiConfig;

    public List<String> getCompletion(String prompt) {

        OpenAiService service = new OpenAiService(openAiConfig.getToken(), Duration.ofSeconds(50));
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model("text-davinci-003")
                .temperature(0.1)
                .maxTokens(2048)
                .echo(true)
                .user("telegramBot")
                .build();
        List<String> choices = new ArrayList<>();
        for (CompletionChoice choice : service.createCompletion(completionRequest).getChoices()) {
            choices.add(choice.getText());
        }
        return choices;
    }
}