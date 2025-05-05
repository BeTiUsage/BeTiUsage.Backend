package home.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubGoalDTO {
    private Long id;
    private String name;
    private boolean completed;
}
