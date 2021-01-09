package academy.service;


import academy.domain.entity.Anime;
import academy.domain.request.AnimeRequest;
import academy.domain.response.AnimeResponse;
import academy.domain.exception.BadRequestException;
import academy.domain.interfaces.IAnimeService;
import academy.domain.mapper.AnimeMapper;
import academy.repository.AnimeRepository;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AnimeService implements IAnimeService {

    private static  List<Anime> animes;

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository){
        this.animeRepository = animeRepository;
    }

   // static {
   //     animes = new ArrayList<>(List.of(Anime.builder().name("Boku no Hero Academy").id(1L).build(),Anime.builder().name("Attak on Titans").id(2L).build()));
   // }

    @Override
    public Page<AnimeResponse> getAllAnimes(Pageable pageable) {
        AnimeMapper mapper = Mappers.getMapper(AnimeMapper.class);
        return animeRepository.findAll(pageable).map(mapper::AnimeToAnimeResponse);
    }

    @Override
    public Anime FindAnimeById(Long id) {
        //animes.stream()
        //        .filter( x -> x.getId().equals(id))
        //       .findFirst()
        //       .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id nao foi encontrado"));

        return animeRepository.findById(id).orElseThrow(() -> new BadRequestException("Id nao foi encontrado"));
    }

    public List<Anime> findAnimesCustom(String nome, LocalDate dataLancamento){
        return animeRepository.find(nome,dataLancamento);
    }

    public Anime SaveAnime(AnimeRequest anime){
        AnimeMapper mapper = Mappers.getMapper(AnimeMapper.class);
        Anime animeEntity = mapper.AnimeRequestToAnime(anime);
        animeRepository.save(animeEntity);
        return animeEntity;
    }

    @Override
    public void DeleteAnime(Long id) {
        animeRepository.delete(FindAnimeById(id));
    }

    public void ReplaceAnime(Long id, Anime anime){
        Anime animeDb = FindAnimeById(id);

        if(animeDb != null){
            BeanUtils.copyProperties(anime,animeDb);
            animes.remove(animeDb);
            animes.add(anime);
        }
    }
}
