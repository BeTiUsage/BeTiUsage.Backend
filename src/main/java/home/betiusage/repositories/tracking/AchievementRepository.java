package home.betiusage.repositories.tracking;

import home.betiusage.entities.tracking.Achievement;
import home.betiusage.enums.tracking.EAchievementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByType(EAchievementType type);
    List<Achievement> findByHobbyId(Long hobbyId);
    List<Achievement> findByIsHobbySpecific(Boolean isHobbySpecific);
}
