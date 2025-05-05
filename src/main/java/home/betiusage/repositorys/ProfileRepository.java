package home.betiusage.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import home.betiusage.entitys.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
