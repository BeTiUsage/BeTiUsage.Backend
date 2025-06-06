package home.betiusage.entities.tracking;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SubGoalTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private GoalTemplate goalTemplate;

    private String title;
    private String description;
    private Integer defaultTargetValue;
    private Integer defaultXpReward;
    private Integer orderIndex;
}
