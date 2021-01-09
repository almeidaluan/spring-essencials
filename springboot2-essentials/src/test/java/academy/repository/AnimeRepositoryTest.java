package academy.repository;

import academy.domain.entity.Anime;
import academy.domain.request.AnimeRequest;
import academy.repository.interfaces.AnimeRepositoryQueries;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for AnimeRepository")
class AnimeRepositoryTest {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeRepositoryTest(AnimeRepository animeRepository){
        this.animeRepository = animeRepository;
    }
    @Test
    @DisplayName("Save anime when successful")
    void save_PersistAnime_WhenSuccessful(){

        Anime anime = Anime.builder()
                .name("Boku no Hero Academy")
                .categoria("Animacao")
                .dataLancamento(LocalDate.of(2020,12,12)).build();

        Anime animeSaved = animeRepository.save(anime);

        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(anime.getName());
    }
}