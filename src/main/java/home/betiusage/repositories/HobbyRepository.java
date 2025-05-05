package home.betiusage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import home.betiusage.entites.Hobby;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
