package home.betiusage.dto.tracking;

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
public class CreateGoalRequest {
    private Long trackingId;
    private Long goalTemplateId;
    private String title;
    private String description;
    private Integer targetValue;
    private Integer xpReward;
    private EGoalType type;
    private EPriority priority;
    private LocalDateTime dueDate;
    private List<CreateSubGoalRequest> subGoals;
}