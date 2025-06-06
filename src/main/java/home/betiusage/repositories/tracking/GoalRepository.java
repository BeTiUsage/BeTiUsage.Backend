package home.betiusage.repositories.tracking;

import home.betiusage.entities.tracking.Goal;
import home.betiusage.enums.tracking.EGoalStatus;
import home.betiusage.enums.tracking.EGoalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByTrackingId(Long trackingId);
    List<Goal> findByTrackingIdAndStatus(Long trackingId, EGoalStatus status);
    List<Goal> findByTrackingIdAndType(Long trackingId, EGoalType type);
    List<Goal> findByTrackingIdAndIsCustom(Long trackingId, Boolean isCustom);

    @Query("SELECT g FROM Goal g WHERE g.tracking.id = :trackingId AND g.dueDate <= :date AND g.status = :status")
    List<Goal> findOverdueGoals(@Param("trackingId") Long trackingId,
                                @Param("date") LocalDateTime date,
                                @Param("status") EGoalStatus status);
}

