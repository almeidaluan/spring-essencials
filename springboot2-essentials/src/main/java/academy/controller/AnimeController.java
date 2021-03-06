package academy.controller;

import academy.domain.entity.Anime;
import academy.domain.request.AnimeRequest;
import academy.domain.response.AnimeResponse;
import academy.domain.interfaces.IAnimeService;
import academy.util.interfaces.IDateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<Page<Anime>> getAnimes(Pageable pageable){
        log.info("Requisicao lista de animes: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.getAllAnimes(pageable));
    }


    @GetMapping("/find-custom")
    public ResponseEntity<List<Anime>> FindCustom(String name, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataLancamento){
        return ResponseEntity.ok(animeService.findAnimesCustom(name, dataLancamento));
    }

    @GetMapping("/{id}")
    public Anime FindAnimeById(@PathVariable Long id){
        return animeService.FindAnimeById(id);
    }

    @GetMapping("/teste-user/{id}")
    public Anime FindAnimeByIdAndReturnUserOn(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        log.info(userDetails.getAuthorities());
        log.info(userDetails.getUsername());
        return animeService.FindAnimeById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
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
