package academy.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class AnimeRequest {

    @NotEmpty(message = "O Campo Nome do Anime nao pode ser nullo nem vazio")
    private String nameAnime;

    @NotEmpty(message = "O Campo Categoria do Anime nao pode ser nullo nem vazio")
    @JsonProperty("categoryAnime")
    private String categoriaAnime;

    private LocalDate dataLancamentoAnime;

}
