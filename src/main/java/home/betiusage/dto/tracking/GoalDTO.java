package home.betiusage.dto.tracking;

import home.betiusage.enums.tracking.EGoalStatus;
import home.betiusage.enums.tracking.EGoalType;
import home.betiusage.enums.tracking.EPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalDTO {
    private Long id;
    private Long trackingId;
    private Long goalTemplateId;
    private String title;
    private String description;
    private Integer targetValue;
    private Integer currentValue;
    private Integer xpReward;
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;
    private LocalDateTime completedDate;
    private EGoalStatus status;
    private EGoalType type;
    private EPriority priority;
    private Boolean isCustom;
    private Double progressPercentage;
    private List<SubGoalDTO> subGoals;
}

