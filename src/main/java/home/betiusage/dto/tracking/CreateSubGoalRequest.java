package home.betiusage.dto.tracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubGoalRequest {
    private String title;
    private String description;
    private Integer targetValue;
    private Integer xpReward;
    private Integer orderIndex;
}
