package academy.repository;

import academy.domain.entity.Anime;
import academy.repository.interfaces.AnimeRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<Anime,Long>, AnimeRepositoryQueries {

}
