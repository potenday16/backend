package com.poemfoot.api.service;

import com.poemfoot.api.domain.W3wResult;
import com.poemfoot.api.domain.Words;
import com.poemfoot.api.domain.poem.Poem;
import com.poemfoot.api.repository.W3wResultRepository;
import com.poemfoot.gpt.dto.request.GptChatPoemRequest;
import com.poemfoot.gpt.dto.response.chat.GptChatPoemResponse;
import com.poemfoot.gpt.exception.toomanyrequest.GptTooManyRequestException;
import com.poemfoot.w3w.W3wProvider;
import com.poemfoot.w3w.dto.W3wWordsResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class W3wService {
    private final W3wResultRepository w3wResultRepository;
    private final W3wProvider w3wProvider;

    @Transactional
    public List<String> requestWords(Double latitude, Double longitude) {

        Optional<W3wResult> w3wResult = w3wResultRepository.findFirstByLatitudeAndLongitude(latitude, longitude);
        if (w3wResult.isPresent()) {
            W3wResult answer = w3wResult.get();
            return answer.getWords().words();
        }

        W3wWordsResponse response = w3wProvider.getWords(latitude, longitude, Locale.KOREA);
        saveW3wResult(latitude, longitude, response);
        return response.words().toList();
    }

    private void saveW3wResult(Double latitude, Double longitude, W3wWordsResponse response) {
        w3wResultRepository.save(new W3wResult(
                response.country(),
                response.nearestPlace(),
                latitude,
                longitude,
                Words.of(response.words().toList()),
                response.language(),
                response.locale(),
                response.map()
        ));
    }
}
