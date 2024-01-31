package com.poemfoot.api.domain.gpt.repository;

import com.poemfoot.api.domain.gpt.domain.GptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GptAnswerRepository extends JpaRepository<GptAnswer,Long> {

}
