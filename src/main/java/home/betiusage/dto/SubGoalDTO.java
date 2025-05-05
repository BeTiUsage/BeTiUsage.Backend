package home.betiusage.dto;

import home.betiusage.entitys.SubGoal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SubGoalDTO {
    private Long id;
    private String name;
    private boolean completed;
}
