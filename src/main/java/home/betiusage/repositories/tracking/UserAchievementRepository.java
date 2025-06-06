package home.betiusage.repositories.tracking;

import home.betiusage.entities.tracking.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
    List<UserAchievement> findByProfileId(Long profileId);
    Optional<UserAchievement> findByProfileIdAndAchievementId(Long profileId, Long achievementId);
    List<UserAchievement> findByProfileIdAndIsNotified(Long profileId, Boolean isNotified);
}
