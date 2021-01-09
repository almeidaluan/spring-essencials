package academy.domain.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AnimeResponse {

    private String nameAnime;
    private String categoriaAnime;
    private LocalDate dataLancamentoAnime;

}
