package home.betiusage.repositories.tracking;

import home.betiusage.entities.tracking.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByTrackingId(Long trackingId);
    List<ActivityLog> findByTrackingIdOrderByTimestampDesc(Long trackingId);
    List<ActivityLog> findByGoalId(Long goalId);

    @Query("SELECT a FROM ActivityLog a WHERE a.tracking.id = :trackingId AND a.timestamp >= :fromDate")
    List<ActivityLog> findRecentActivities(@Param("trackingId") Long trackingId,
                                           @Param("fromDate") LocalDateTime fromDate);
}
