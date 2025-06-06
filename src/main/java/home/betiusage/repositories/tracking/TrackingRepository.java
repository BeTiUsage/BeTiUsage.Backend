package home.betiusage.repositories.tracking;

import home.betiusage.entities.tracking.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    List<Tracking> findByProfileId(Long profileId);
    List<Tracking> findByProfileIdAndIsActive(Long profileId, Boolean isActive);
    Optional<Tracking> findByProfileIdAndHobbyId(Long profileId, Long hobbyId);
    List<Tracking> findByHobbyId(Long hobbyId);

    @Query("SELECT t FROM Tracking t WHERE t.profile.id = :profileId ORDER BY t.totalXp DESC")
    List<Tracking> findByProfileIdOrderByXpDesc(@Param("profileId") Long profileId);
}