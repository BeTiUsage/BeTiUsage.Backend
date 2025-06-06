package home.betiusage.repositories.tracking;

import home.betiusage.entities.tracking.GoalTemplate;
import home.betiusage.enums.tracking.EGoalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalTemplateRepository extends JpaRepository<GoalTemplate, Long> {
    List<GoalTemplate> findByHobbyId(Long hobbyId);
    List<GoalTemplate> findByHobbyIdAndIsActive(Long hobbyId, Boolean isActive);
    List<GoalTemplate> findByHobbyIdAndLevelRequirementLessThanEqual(Long hobbyId, Integer level);
    List<GoalTemplate> findByType(EGoalType type);
}