package com.poemfoot.api.domain;

import com.poemfoot.api.converter.WordsConverter;
import com.poemfoot.w3w.dto.W3wWords;
import com.poemfoot.w3w.dto.W3wWordsResponse;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"latitude", "longitude"})
})
@NoArgsConstructor
public class W3wResult {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String nearestPlace;
    private Double latitude;
    private Double longitude;
    @Convert(converter = WordsConverter.class)
    private Words words;
    private String language;
    private String locale;
    private String map;

    public W3wResult(String country, String nearestPlace, Double latitude, Double longitude,
            Words words, String language, String locale, String map) {
        this.country = country;
        this.nearestPlace = nearestPlace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.words = words;
        this.language = language;
        this.locale = locale;
        this.map = map;
    }
}
