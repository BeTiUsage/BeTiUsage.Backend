package home.betiusage.entities.tracking;

import home.betiusage.entities.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;

    @ManyToOne(fetch = FetchType.EAGER)
    private Achievement achievement;

    private LocalDateTime unlockedDate;
    private Boolean isNotified = false;
}

