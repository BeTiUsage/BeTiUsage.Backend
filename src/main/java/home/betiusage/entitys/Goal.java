package home.betiusage.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean completed;
    @OneToMany(mappedBy = "goal", fetch = FetchType.EAGER) //cascade = CascadeType.ALL here?
    private Set<SubGoal> subGoals = new HashSet<>();
    @ManyToOne
    private Tracking tracking;

}
