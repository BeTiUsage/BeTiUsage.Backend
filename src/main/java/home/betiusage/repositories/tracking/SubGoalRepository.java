package home.betiusage.repositories.tracking;

import home.betiusage.entities.tracking.SubGoal;
import home.betiusage.enums.tracking.EGoalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubGoalRepository extends JpaRepository<SubGoal, Long> {
    List<SubGoal> findByGoalId(Long goalId);
    List<SubGoal> findByGoalIdAndStatus(Long goalId, EGoalStatus status);
    List<SubGoal> findByGoalIdOrderByOrderIndex(Long goalId);
}