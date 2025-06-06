package home.betiusage.dto.tracking;

import home.betiusage.enums.tracking.EGoalType;
import home.betiusage.enums.tracking.EPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalTemplateDTO {
    private Long id;
    private Long hobbyId;
    private String title;
    private String description;
    private Integer defaultTargetValue;
    private Integer defaultXpReward;
    private EGoalType type;
    private EPriority priority;
    private Boolean isActive;
    private Integer levelRequirement;
    private List<SubGoalTemplateDTO> subGoalTemplates;
}