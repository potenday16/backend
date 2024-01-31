package com.poemfoot.api.domain.gpt.repository;

import com.poemfoot.api.domain.gpt.domain.GptQuestion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GptQuestionRepository extends JpaRepository<GptQuestion, Long> {

    Optional<GptQuestion> findFirstByQuestion(String question);
}
