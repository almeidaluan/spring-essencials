package academy.controller;

import academy.domain.entity.Anime;
import academy.domain.interfaces.IAnimeService;
import academy.domain.response.AnimeResponse;
import academy.util.AnimeCreator;
import academy.util.interfaces.IDateUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private IAnimeService animeService;

    @Mock
    private IDateUtil dateUtil;

    @BeforeEach
    void setUp(){

        PageImpl<Anime> animePage = new PageImpl<>
                (List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeService.getAllAnimes(ArgumentMatchers.any())).thenReturn(animePage);
        BDDMockito.when(animeService.findAnimesCustom(ArgumentMatchers.anyString(),ArgumentMatchers.any()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

    }

    @Test
    @DisplayName("returns list of anime when successful")
    void getAllAnimes_ReturnAnimesWhenSuccessful(){
        Page<Anime> animePage = animeController.getAnimes(null).getBody();

        String expectedName = AnimeCreator.createAnimeToBeSaved().getNameAnime();

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("return list of anime by Name when successful")
    void findAnimesCustom_ReturnAnimesWithNameWhenSuccessful(){
        List<Anime> animes = animeController.FindCustom("Hajime no Ippo",null).getBody();

        String expectedName = AnimeCreator.createValidAnime().getName();

        Assertions.assertThat(animes).isNotNull();

        Assertions.assertThat(animes)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("return list of anime by DataLancamento when successful")
    void findAnimesCustom_ReturnAnimesWithLaunchWhenSuccessful(){
        List<Anime> animes = animeController.FindCustom("",LocalDate.of(2020,12,31)).getBody();

        String expectedName = AnimeCreator.createValidAnime().getName();

        Assertions.assertThat(animes).isNotNull();

        Assertions.assertThat(animes)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }
}