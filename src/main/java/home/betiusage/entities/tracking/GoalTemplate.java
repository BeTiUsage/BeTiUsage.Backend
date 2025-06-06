package home.betiusage.entities.tracking;

import home.betiusage.entities.Hobby;
import home.betiusage.enums.tracking.EGoalType;
import home.betiusage.enums.tracking.EPriority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GoalTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hobby hobby;

    private String title;
    private String description;
    private Integer defaultTargetValue;
    private Integer defaultXpReward;

    @Enumerated(EnumType.STRING)
    private EGoalType type;

    @Enumerated(EnumType.STRING)
    private EPriority priority = EPriority.MEDIUM;

    private Boolean isActive = true;
    private Integer levelRequirement = 1;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SubGoalTemplate> subGoalTemplates = new ArrayList<>();
}