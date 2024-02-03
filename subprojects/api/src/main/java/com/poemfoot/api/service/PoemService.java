package com.poemfoot.api.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.poemfoot.api.domain.poem.Poem;
import com.poemfoot.api.domain.Words;
import com.poemfoot.api.domain.poem.PoemAnswer;
import com.poemfoot.api.exception.notfound.poem.NotFoundPoemException;
import com.poemfoot.api.repository.PoemRepository;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import com.poemfoot.gpt.exception.toomanyrequest.GptTooManyRequestException;
import com.poemfoot.gpt.service.GptPoemProvider;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PoemService {
    private static final String TITLE_KEY = "제목";
    private static final String CONTENT_KEY = "내용";

    private final GptPoemProvider gptPoemProvider;
    private final PoemRepository poemRepository;

    @Transactional
    public GptChatPoemResponse requestPoem(List<String> words, String location) {
        GptChatPoemRequest request = new GptChatPoemRequest(words, location);

        Optional<Poem> poem = poemRepository.findFirstByGptRequestHash(getHash(request));
        if (poem.isPresent()) {
            Poem answer = poem.get();
            return new GptChatPoemResponse(answer.getContent());
        }

        return gptPoemProvider.completionChat(request)
                .orElseThrow(GptTooManyRequestException::new);
    }

    @Transactional
    public Poem savePoem(List<String> words, String location, GptChatPoemResponse response) {
        GptChatPoemRequest request = new GptChatPoemRequest(words, location);

        Words saveWords = getWords(request);
        PoemAnswer answer = getAnswer(response);
        return poemRepository.save(new Poem(answer.title(),answer.content(),saveWords, location,
                getHash(request)));
    }

    public Poem findPoem(List<String> words, String location) {
        GptChatPoemRequest request = new GptChatPoemRequest(words, location);
        return poemRepository.findFirstByGptRequestHash(getHash(request))
                .orElseThrow(NotFoundPoemException::new);
    }

    public Poem findPoem(Long poemId) {
        return poemRepository.findById(poemId)
                .orElseThrow(NotFoundPoemException::new);
    }

    private Words getWords(GptChatPoemRequest request) {
        return Words.of(request.getWords());
    }

    private String getHash(GptChatPoemRequest request) {
        return Stream.concat(request.getWords().stream(), Stream.of(request.getLocation()))
                .collect(Collectors.joining(","));
    }

    private PoemAnswer getAnswer(GptChatPoemResponse response) {
        return parseAnswer(response);
    }

    private PoemAnswer parseAnswer(GptChatPoemResponse response) {

        String jsonString = responseToMessage(response);
        jsonString = jsonString.substring(jsonString.indexOf("{"), jsonString.indexOf("}") + 1);
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        return PoemAnswer.of(
                jsonObject.get(TITLE_KEY).getAsString(),
                jsonObject.get(CONTENT_KEY).getAsString());
    }

    private String responseToMessage(GptChatPoemResponse response) {
        return response.getMessages().stream()
                .toList()
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(NotFoundPoemException::new);
    }
}
