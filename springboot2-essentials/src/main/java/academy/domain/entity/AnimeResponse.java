package academy.domain.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimeResponse {

    private String nameAnime;
    private String categoriaAnime;
    private LocalDate dataLancamentoAnime;

}
