package academy.domain.mapper;


import academy.domain.entity.Teste;
import academy.domain.request.TesteRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TesteMapper {
    Teste TesteRequestToTeste(TesteRequest testeRequest);
}
