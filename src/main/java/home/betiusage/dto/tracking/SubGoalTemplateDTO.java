package home.betiusage.dto.tracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubGoalTemplateDTO {
    private Long id;
    private Long goalTemplateId;
    private String title;
    private String description;
    private Integer defaultTargetValue;
    private Integer defaultXpReward;
    private Integer orderIndex;
}