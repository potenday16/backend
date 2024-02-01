package com.poemfoot.api.repository;

import com.poemfoot.api.domain.GptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GptAnswerRepository extends JpaRepository<GptAnswer,Long> {

}
