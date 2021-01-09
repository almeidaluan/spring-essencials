package academy.util;

import academy.domain.entity.Anime;
import academy.domain.response.AnimeResponse;

import java.time.LocalDate;

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
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .name("Hajime no Ippo 2")
                .id(1L)
                .build();
    }
}
