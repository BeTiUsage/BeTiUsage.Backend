package home.betiusage.entities.tracking;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tracking tracking;

    @ManyToOne(fetch = FetchType.LAZY)
    private Goal goal;

    @ManyToOne(fetch = FetchType.LAZY)
    private SubGoal subGoal;

    private String activityType;
    private String description;
    private Integer xpGained;
    private LocalDateTime timestamp;
    private String notes;
}