package home.betiusage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import home.betiusage.entites.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
