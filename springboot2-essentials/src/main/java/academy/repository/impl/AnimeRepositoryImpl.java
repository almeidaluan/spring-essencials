package academy.repository.impl;

import academy.domain.entity.Anime;
import academy.repository.interfaces.AnimeRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Repository
public class AnimeRepositoryImpl implements AnimeRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Anime> find(String name, LocalDate dataLancamento) {

        var jpql = new StringBuilder();
        jpql.append("from ANIME where 0 = 0 ");

        var parametros = new HashMap<String, Object>();

        if (StringUtils.hasLength(name)) {
            jpql.append("and name like :name ");
            parametros.put("name", "%" + name + "%");
        }

        if (dataLancamento != null) {
            jpql.append("and dataLancamento >= :datalancamento ");
            parametros.put("datalancamento", dataLancamento);
        }


        TypedQuery<Anime> query = manager
                .createQuery(jpql.toString(), Anime.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }
}
