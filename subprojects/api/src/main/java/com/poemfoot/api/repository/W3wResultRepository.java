package com.poemfoot.api.repository;

import com.poemfoot.api.domain.W3wResult;
import com.poemfoot.api.domain.poem.Poem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface W3wResultRepository extends JpaRepository<W3wResult,Long> {
    Optional<W3wResult> findFirstByLatitudeAndLongitude(Double latitude, Double longitude);
}
