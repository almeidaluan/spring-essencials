package academy.domain.mapper;


import academy.domain.entity.Anime;
import academy.domain.entity.AnimeRequest;
import academy.domain.entity.Teste;
import academy.domain.entity.TesteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TesteMapper {
    Teste TesteRequestToTeste(TesteRequest testeRequest);
}
