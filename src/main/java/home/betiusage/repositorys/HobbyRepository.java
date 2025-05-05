package home.betiusage.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import home.betiusage.entitys.Hobby;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
