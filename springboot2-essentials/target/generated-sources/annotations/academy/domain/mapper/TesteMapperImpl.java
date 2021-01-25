package academy.domain.mapper;

import academy.domain.entity.Teste;
import academy.domain.request.TesteRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T02:38:03-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class TesteMapperImpl implements TesteMapper {

    @Override
    public Teste TesteRequestToTeste(TesteRequest testeRequest) {
        if ( testeRequest == null ) {
            return null;
        }

        Teste teste = new Teste();

        teste.setCategoria( testeRequest.getCategoria() );

        return teste;
    }
}
