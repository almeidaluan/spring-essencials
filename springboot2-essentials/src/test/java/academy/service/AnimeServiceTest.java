package academy.service;

import academy.domain.entity.Anime;
import academy.domain.interfaces.IAnimeService;
import academy.repository.AnimeRepository;
import academy.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    @InjectMocks
    private AnimeService service;

    @Mock
    private AnimeRepository animeRepository;

    @BeforeEach
    void  setUp(){

        PageImpl<Anime> animePage = new PageImpl<>
                (List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(animePage);

        BDDMockito.when(animeRepository.find(ArgumentMatchers.anyString(),ArgumentMatchers.any()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(AnimeCreator.createValidAnimeOptional());

        BDDMockito.doNothing().when(animeRepository).delete(ArgumentMatchers.any());

        BDDMockito.when(animeRepository.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(AnimeCreator.createValidAnime());
    }


    @Test
    @DisplayName("returns list of anime when successful")
    void getAllAnimes_ReturnAnimesWhenSuccessful(){
        Page<Anime> animePage = service.getAllAnimes(PageRequest.of(1,1));

        String expectedName = AnimeCreator.createAnimeToBeSaved().getNameAnime();

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("returns list of anime when successful")
    void findAllCustom_ReturnAnimesWhenSuccessful(){

        List<Anime> animes = service.findAnimesCustom("Hajime no Ippo",null);

        String expectedName = AnimeCreator.createValidAnime().getName();

        Assertions.assertThat(animes).isNotNull();

        Assertions.assertThat(animes)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("return Anime by Id when successful")
    void findById_ReturnAnimeWithIdWhenSuccessful(){
        Anime anime = service.FindAnimeById(1L);

        String expectedName = AnimeCreator.createValidAnime().getName();

        Assertions.assertThat(anime).isNotNull();
        Assertions.assertThat(anime.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("delete removes anime when successful")
    void delete_RemovesAnime_WhenSuccessful(){

        Assertions.assertThatCode(() ->service.DeleteAnime(1L))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("save returns anime when successful")
    void save_ReturnsAnime_WhenSuccessful(){

        Anime anime = service.SaveAnime(AnimeCreator.createValidAnimeToSave());
        anime.setId(1L);

        Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime02());

    }
}