package academy.service;

import academy.domain.entity.UserAcademy;
import academy.repository.UserAcademyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAcademyService implements UserDetailsService {

    private final UserAcademyRepository userAcademyRepository;

    public UserAcademyService(UserAcademyRepository userAcademyRepository){
        this.userAcademyRepository = userAcademyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        return Optional.ofNullable(userAcademyRepository.findByUsername(name)).orElseThrow( () -> new UsernameNotFoundException("Usuario Nao encontrado no sistema"));
    }
}
