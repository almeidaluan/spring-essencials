package academy.domain.mapper;

import academy.domain.entity.Anime;
import academy.domain.entity.Anime.AnimeBuilder;
import academy.domain.request.AnimeRequest;
import academy.domain.response.AnimeResponse;
import academy.domain.response.AnimeResponse.AnimeResponseBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T18:46:00-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class AnimeMapperImpl implements AnimeMapper {

    @Override
    public Anime AnimeRequestToAnime(AnimeRequest animeRequest) {
        if ( animeRequest == null ) {
            return null;
        }

        AnimeBuilder anime = Anime.builder();

        anime.name( animeRequest.getNameAnime() );
        anime.categoria( animeRequest.getCategoriaAnime() );
        anime.dataLancamento( animeRequest.getDataLancamentoAnime() );

        return anime.build();
    }

    @Override
    public AnimeResponse AnimeToAnimeResponse(Anime anime) {
        if ( anime == null ) {
            return null;
        }

        AnimeResponseBuilder animeResponse = AnimeResponse.builder();

        animeResponse.nameAnime( anime.getName() );
        animeResponse.categoriaAnime( anime.getCategoria() );
        animeResponse.dataLancamentoAnime( anime.getDataLancamento() );

        return animeResponse.build();
    }

    @Override
    public List<AnimeResponse> AnimeToAnimeResponse(List<Anime> anime) {
        if ( anime == null ) {
            return null;
        }

        List<AnimeResponse> list = new ArrayList<AnimeResponse>( anime.size() );
        for ( Anime anime1 : anime ) {
            list.add( AnimeToAnimeResponse( anime1 ) );
        }

        return list;
    }
}
