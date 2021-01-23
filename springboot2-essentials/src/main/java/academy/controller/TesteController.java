package academy.controller;

import academy.domain.entity.Teste;
import academy.domain.request.AnimeRequest;
import academy.domain.request.TesteRequest;
import academy.domain.mapper.TesteMapper;
import academy.repository.AnimeRepository;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("testes")
@Log4j2
public class TesteController {

    @Autowired
    private AnimeRepository animeRepository;


    @PostMapping
    public ResponseEntity<Teste> SaveAnime(@RequestBody TesteRequest testeRequest){
        TesteMapper mapper = Mappers.getMapper(TesteMapper.class);
        Teste teste = mapper.TesteRequestToTeste(testeRequest);
        return new ResponseEntity<>(teste, HttpStatus.CREATED);
    }

    @GetMapping
    public String teste(){
        animeRepository.findAlgumacoisa();
        return "foi";
    }
}
