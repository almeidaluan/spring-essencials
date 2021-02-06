package academy.repository;

import academy.domain.entity.UserAcademy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAcademyRepository extends JpaRepository<UserAcademy,Long> {

    UserAcademy findByUsername(String name);
}
