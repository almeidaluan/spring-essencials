package academy.client;

import academy.domain.entity.Anime;
import academy.domain.request.AnimeRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class ClientMain {

    public static void main(String... args){
        ResponseEntity<Anime> entity =  new RestTemplate().getForEntity("http://localhost:8080/animes/{id}",Anime.class,1);
        log.info(entity.toString());

        Anime object =  new RestTemplate().getForObject("http://localhost:8080/animes/{id}",Anime.class,3);
        log.info(object.toString());

        Anime[] allAnimes =  new RestTemplate().getForObject("http://localhost:8080/animes/find-custom",Anime[].class);
        log.info(Arrays.toString(allAnimes));

        ResponseEntity<List<Anime>> allAnimes02 =  new RestTemplate().exchange("http://localhost:8080/animes/find-custom",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {});
        log.info(allAnimes02.getBody());

        AnimeRequest animeRequest = AnimeRequest.builder().nameAnime("Kingdom").categoriaAnime("Cavaleiros").
                dataLancamentoAnime(LocalDate.of(2020,12,12)).build();

        Anime animeSaved =  new RestTemplate().postForObject("http://localhost:8080/animes",
                animeRequest, Anime.class);
        log.info(animeSaved.toString());

        AnimeRequest animeRequest02 = AnimeRequest.builder().nameAnime("Samurai Champloo").categoriaAnime("Samurais").
                dataLancamentoAnime(LocalDate.of(2020,12,12)).build();

        ResponseEntity<Anime> animeSaved02 =  new RestTemplate().exchange("http://localhost:8080/animes",HttpMethod.POST
                ,new HttpEntity<>(animeRequest02,createJsonHeaders()), Anime.class);
        log.info("Anime salvo {} ",animeSaved02.toString());

    }

    public static HttpHeaders createJsonHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
