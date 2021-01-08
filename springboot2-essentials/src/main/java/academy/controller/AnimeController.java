package academy.controller;

import academy.domain.entity.Anime;
import academy.domain.entity.AnimeRequest;
import academy.domain.entity.AnimeResponse;
import academy.domain.interfaces.IAnimeService;
import academy.domain.mapper.AnimeMapper;
import academy.util.interfaces.IDateUtil;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("animes")
@Log4j2
public class AnimeController {

    private IDateUtil dateUtil;
    private IAnimeService animeService;

    public AnimeController(IDateUtil dateUtil, IAnimeService animeService){
        this.dateUtil = dateUtil;
        this.animeService = animeService;
    }

    @GetMapping
    public Page<AnimeResponse> getAnimes(Pageable pageable){
        log.info("Requisicao lista de animes: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return animeService.getAllAnimes(pageable);
    }
    @GetMapping("/find-custom")
    public ResponseEntity<List<Anime>> FindCustom(String nome,  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataLancamento){
        return ResponseEntity.ok(animeService.findAnimesCustom(nome, dataLancamento));
    }

    @GetMapping("/{id}")
    public Optional<Anime> FindAnimeById(@PathVariable Long id){
        return animeService.FindAnimeById(id);
    }

    @PostMapping
    public ResponseEntity<Anime> SaveAnime(@RequestBody @Valid AnimeRequest animeRequest){
        return new ResponseEntity<>(animeService.SaveAnime(animeRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> RemoveAnime(@PathVariable Long id){
        animeService.DeleteAnime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> RemoveAnime(@RequestBody Anime anime){
        animeService.ReplaceAnime(anime.getId(),anime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
