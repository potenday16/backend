package com.poemfoot.gpt.domain.question;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GptQuestionRepository extends JpaRepository<GptQuestion, Long> {

    Optional<GptQuestion> findFirstByQuestion(String question);
}
