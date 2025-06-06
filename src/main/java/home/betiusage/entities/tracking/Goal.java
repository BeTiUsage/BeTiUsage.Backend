package home.betiusage.entities.tracking;

import home.betiusage.enums.tracking.EGoalStatus;
import home.betiusage.enums.tracking.EGoalType;
import home.betiusage.enums.tracking.EPriority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tracking tracking;

    @ManyToOne(fetch = FetchType.EAGER)
    private GoalTemplate goalTemplate;

    private String title;
    private String description;
    private Integer targetValue;
    private Integer currentValue = 0;
    private Integer xpReward;
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;
    private LocalDateTime completedDate;

    @Enumerated(EnumType.STRING)
    private EGoalStatus status = EGoalStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private EGoalType type;

    @Enumerated(EnumType.STRING)
    private EPriority priority = EPriority.MEDIUM;

    private Boolean isCustom = false;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubGoal> subGoals = new ArrayList<>();

    @Transient
    public Boolean isCompleted() {
        return this.currentValue >= this.targetValue;
    }

    @Transient
    public Double getProgressPercentage() {
        if (targetValue == 0) return 100.0;
        return Math.min(100.0, (currentValue.doubleValue() / targetValue) * 100);
    }

    public Goal(Long id) {
        this.id = id;
    }
}
