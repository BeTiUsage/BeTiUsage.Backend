package home.betiusage.entities.tracking;

import home.betiusage.enums.tracking.EGoalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SubGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Goal goal;

    private String title;
    private String description;
    private Integer targetValue;
    private Integer currentValue = 0;
    private Integer xpReward;
    private LocalDateTime completedDate;

    @Enumerated(EnumType.STRING)
    private EGoalStatus status = EGoalStatus.ACTIVE;

    private Integer orderIndex;

    @Transient
    public Boolean isCompleted() {
        return this.currentValue >= this.targetValue;
    }

    public SubGoal(Long id) {
        this.id = id;
    }
}