package academy.repository.impl;

import academy.domain.entity.Anime;
import academy.domain.entity.RetornoNativeQuery;
import academy.repository.interfaces.AnimeRepositoryQueries;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class AnimeRepositoryImpl implements AnimeRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    public  List<Anime> findAlgumacoisa(){

        var jpql = new StringBuilder();
        //jpql.append("select e.NAME as Name,e.ID as IdAnime,c.ANIME_ID as IdCategoria from ANIME e JOIN CATEGORIA c on c.ANIME_ID = e.ID  "); createNativeQuery Query query = manager.createNativeQuery(jpql.toString());
        //jpql.append("select e.id as IdAnime,c.animeId as IdCategoria from ANIME e JOIN CATEGORIA c on c.animeId = e.id  "); createQuery

        jpql.append("select e.id as IdAnime,c.animeId as IdCategoria from ANIME e JOIN CATEGORIA c on c.animeId = e.id  ");

        TypedQuery<Object[]> query = manager.createQuery(jpql.toString(),Object[].class);
        var animes =  query.getResultList();

        List<RetornoNativeQuery> animesList = new ArrayList<RetornoNativeQuery>();
        for (var teste: animes) {
            RetornoNativeQuery anime = new RetornoNativeQuery();
            anime.setIdAnime((Long) teste[0]);
            anime.setIdCategoria((Long) teste[1]);

            animesList.add(anime);

        }

        //Query query = manager.createQuery(jpql.toString());
        // ou TypedQuery<Anime[]> query = manager.createQuery(jpql.toString(), Anime[].class);
        //List<Anime[]> animes = query.getResultList();
        return null;
    }

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
