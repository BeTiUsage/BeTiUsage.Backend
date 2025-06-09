package home.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GoalDTO {
    private Long id;
    private String name;
    private Boolean completed;
    private List<SubGoalDTO> subGoals;
    private Long trackingId;
    private Long goalNumber;
    private String hobbyName;
    private Boolean isTemplate;
}
