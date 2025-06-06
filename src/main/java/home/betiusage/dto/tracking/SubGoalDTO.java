package home.betiusage.dto.tracking;

import home.betiusage.enums.tracking.EGoalStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubGoalDTO {
    private Long id;
    private Long goalId;
    private String title;
    private String description;
    private Integer targetValue;
    private Integer currentValue;
    private Integer xpReward;
    private LocalDateTime completedDate;
    private EGoalStatus status;
    private Integer orderIndex;
    private Boolean isCompleted;
}