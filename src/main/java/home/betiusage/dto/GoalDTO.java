package home.betiusage.dto;

import home.betiusage.entities.SubGoal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GoalDTO {
    private Long id;
    private String name;
    private Boolean completed;
    private List<SubGoalDTO> subGoals;
    private Long trackingId;
}
