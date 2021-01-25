package academy.integration;

import academy.domain.entity.Anime;
import academy.domain.exception.BadRequestException;
import academy.domain.response.AnimeResponse;
import academy.repository.AnimeRepository;
import academy.util.AnimeCreator;
import academy.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class AnimeControllerIntegration {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("list returns list of anime inside page object when successful")
    void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful() {
        Anime savedAnime = animeRepository.save(AnimeCreator.createValidAnime());

        String expectedName = savedAnime.getName();

        PageableResponse<Anime> animePage = testRestTemplate.exchange("/animes", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Anime>>() {
                }).getBody();

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("return list of anime by Name when successful")
    void findAnimesCustomName_ReturnAnimesWithNameWhenSuccessful(){

        Anime savedAnime = animeRepository.save(AnimeCreator.createValidAnime());

        String expectedName = savedAnime.getName();

        List<Anime> animes = testRestTemplate.exchange("/animes/find-custom?name=Hajime", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Anime>>() {
                }).getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("return list of anime by Data when successful")
    void findAnimesCustomData_ReturnAnimesWithDataWhenSuccessful(){

        Anime savedAnime = animeRepository.save(AnimeCreator.createValidAnime());

        String expectedName = savedAnime.getName();
        String data = "2020-12-20";
        String url = String.format("/animes/find-custom?dataLancamento=%s",data);
        List<Anime> animes = testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Anime>>() {
                }).getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save returns anime when successful")
    void save_ReturnsAnime_WhenSuccessful(){

        ResponseEntity<Anime> anime = testRestTemplate.exchange("/animes",HttpMethod.POST
                ,new HttpEntity<>(AnimeCreator.createValidAnimeToSave(),null), Anime.class);

        Assertions.assertThat(anime)
                .isNotNull();

        Assertions.assertThat(anime.getBody().getName()).isEqualTo(AnimeCreator.createValidAnimeToSave().getNameAnime());
        Assertions.assertThat(anime.getBody().getCategoria()).isEqualTo(AnimeCreator.createValidAnimeToSave().getCategoriaAnime());
        Assertions.assertThat(anime.getBody().getDataLancamento()).isEqualTo(AnimeCreator.createValidAnimeToSave().getDataLancamentoAnime());
        Assertions.assertThat(anime.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    @DisplayName("return Anime by Id when successful")
    void findById_ReturnAnimeWithIdWhenSuccessful(){

        Anime savedAnime = animeRepository.save(AnimeCreator.createValidAnime());

        Long expectedId = savedAnime.getId();

        Anime anime = testRestTemplate.getForObject("/animes/{id}",Anime.class,expectedId);

        Assertions.assertThat(anime).isNotNull();
        Assertions.assertThat(anime.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes anime when successful")
    void delete_RemovesAnime_WhenSuccessful(){

        Anime savedAnime = animeRepository.save(AnimeCreator.createValidAnime());

        ResponseEntity<Void> result = testRestTemplate.exchange("/animes/{id}",HttpMethod.DELETE
                ,new HttpEntity<>(AnimeCreator.createValidAnimeToSave(),null), Void.class,savedAnime.getId());

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}
