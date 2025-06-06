package home.betiusage.dto.tracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogDTO {
    private Long id;
    private Long trackingId;
    private Long goalId;
    private Long subGoalId;
    private String activityType;
    private String description;
    private Integer xpGained;
    private LocalDateTime timestamp;
    private String notes;
}
