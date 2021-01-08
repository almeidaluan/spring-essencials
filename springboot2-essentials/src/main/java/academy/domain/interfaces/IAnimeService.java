package academy.domain.interfaces;

import academy.domain.entity.Anime;
import academy.domain.entity.AnimeRequest;
import academy.domain.entity.AnimeResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAnimeService {

    List<AnimeResponse> getAllAnimes();
    Optional<Anime> FindAnimeById(Long id);
    Anime SaveAnime(AnimeRequest anime);
    void DeleteAnime(Long id);
    void ReplaceAnime(Long id, Anime anime);
    List<Anime> findAnimesCustom(String nome, LocalDate dataLancamento);
}
