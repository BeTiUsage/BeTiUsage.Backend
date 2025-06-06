package home.betiusage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import home.betiusage.entities.Community;
import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
}
