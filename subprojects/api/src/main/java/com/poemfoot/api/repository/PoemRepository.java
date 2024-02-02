package com.poemfoot.api.repository;

import com.poemfoot.api.domain.poem.Poem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoemRepository extends JpaRepository<Poem, Long> {
    Optional<Poem> findFirstByGptRequestHash(String gptRequestHash);
}
