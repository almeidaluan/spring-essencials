package academy.domain.mapper;

import academy.domain.entity.Anime;
import academy.domain.entity.AnimeRequest;
import academy.domain.entity.AnimeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimeMapper {

    @Mappings({
            @Mapping(target="name", source="animeRequest.nameAnime"),
            @Mapping(target="categoria", source="animeRequest.categoriaAnime"),
            @Mapping(target="dataLancamento", source="animeRequest.dataLancamentoAnime")
    })
    Anime AnimeRequestToAnime(AnimeRequest animeRequest);

    @Mappings({
            @Mapping(source="anime.name", target="nameAnime"),
            @Mapping(source="anime.categoria", target="categoriaAnime"),
            @Mapping(source="anime.dataLancamento", target="dataLancamentoAnime")
    })
    AnimeResponse AnimeRequestToAnime(Anime anime);


    @Mappings({
            @Mapping(source="name", target="nameAnime"),
            @Mapping(source="categoria", target="categoriaAnime"),
            @Mapping(source="dataLancamento", target="dataLancamentoAnime")
    })
    List<AnimeResponse> AnimeToAnimeResponse(List<Anime> anime);
}
