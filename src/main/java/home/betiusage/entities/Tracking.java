package home.betiusage.entities;

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
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Profile profile;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Goal> goals = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Hobby hobby;
    private Double moneySpent;
    private Integer xp;
    private LocalDateTime startDate;

}
