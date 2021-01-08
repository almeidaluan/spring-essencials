package academy.controller;

import academy.domain.entity.Anime;
import academy.domain.entity.AnimeRequest;
import academy.domain.entity.Teste;
import academy.domain.entity.TesteRequest;
import academy.domain.mapper.TesteMapper;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testes")
@Log4j2
public class TesteController {

    @PostMapping
    public ResponseEntity<Teste> SaveAnime(@RequestBody TesteRequest testeRequest){
        TesteMapper mapper = Mappers.getMapper(TesteMapper.class);
        Teste teste = mapper.TesteRequestToTeste(testeRequest);
        return new ResponseEntity<>(teste, HttpStatus.CREATED);
    }
}
