package academy.domain.interfaces;

import academy.domain.entity.Anime;
import academy.domain.request.AnimeRequest;
import academy.domain.response.AnimeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAnimeService {

    Page<AnimeResponse> getAllAnimes(Pageable pageable);
    Optional<Anime> FindAnimeById(Long id);
    Anime SaveAnime(AnimeRequest anime);
    void DeleteAnime(Long id);
    void ReplaceAnime(Long id, Anime anime);
    List<Anime> findAnimesCustom(String nome, LocalDate dataLancamento);
}
