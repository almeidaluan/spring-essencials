package academy.repository;

import academy.domain.entity.Anime;
import academy.domain.exception.BadRequestException;
import academy.domain.request.AnimeRequest;
import academy.repository.interfaces.AnimeRepositoryQueries;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Test
    @DisplayName("Delete anime when successful")
    void delete_RemoveAnime_WhenSuccessful(){

        Anime anime = Anime.builder()
                .name("Boku no Hero Academy")
                .categoria("Animacao")
                .dataLancamento(LocalDate.of(2020,12,12)).build();

        Anime animeSaved = animeRepository.save(anime);

        animeRepository.delete(animeSaved);

        Optional<Anime> animeDatabase = animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeDatabase).isEmpty();

    }

    @Test
    @DisplayName("Return List whith values when Find by Name")
    void Find_FindByNameAnime_WhenSuccessful(){

        Anime anime = Anime.builder()
                .name("Boku no Hero Academy")
                .categoria("Animacao")
                .dataLancamento(LocalDate.of(2020,12,12)).build();

        Anime animeSaved = animeRepository.save(anime);

        List<Anime> animes = animeRepository.find("Boku no Hero Academy",null);

        Assertions.assertThat(animes).isNotNull();
        Assertions.assertThat(animes).contains(animeSaved);
    }


}