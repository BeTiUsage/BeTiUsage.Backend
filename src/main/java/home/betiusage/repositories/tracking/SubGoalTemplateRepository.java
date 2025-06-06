package home.betiusage.repositories.tracking;

import home.betiusage.entities.tracking.SubGoalTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubGoalTemplateRepository extends JpaRepository<SubGoalTemplate, Long> {
    List<SubGoalTemplate> findByGoalTemplateId(Long goalTemplateId);
    List<SubGoalTemplate> findByGoalTemplateIdOrderByOrderIndex(Long goalTemplateId);
}