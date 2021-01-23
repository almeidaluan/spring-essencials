package academy.repository.interfaces;


import academy.domain.entity.Anime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AnimeRepositoryQueries {

    List<Anime> find(String name, LocalDate dataLancamento);
    List<Anime> findAlgumacoisa();
}
