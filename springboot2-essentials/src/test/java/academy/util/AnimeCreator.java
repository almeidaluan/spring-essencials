package academy.util;

import academy.domain.entity.Anime;
import academy.domain.request.AnimeRequest;
import academy.domain.response.AnimeResponse;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Optional;

public class AnimeCreator {

    public static AnimeResponse createAnimeToBeSaved(){
        return AnimeResponse.builder()
                .nameAnime("Hajime no Ippo")
                .categoriaAnime("Animacao")
                .dataLancamentoAnime(LocalDate.of(2020,12,12))
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .name("Hajime no Ippo")
                .dataLancamento(LocalDate.of(2020,12,31))
                .build();
    }

    public static AnimeRequest createValidAnimeToSave(){
        return AnimeRequest.builder()
                .nameAnime("Hajime no Ippo")
                .dataLancamentoAnime(LocalDate.of(2020,12,31))
                .build();
    }

    public static Optional<Anime> createValidAnimeOptional(){
        return Optional.of(Anime.builder()
                .name("Hajime no Ippo")
                .dataLancamento(LocalDate.of(2020,12,31))
                .id(1L)
                .build());
    }

    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .name("Hajime no Ippo 2")
                .id(1L)
                .build();
    }
}
