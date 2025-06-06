package home.betiusage.entities.tracking;

import home.betiusage.entities.Hobby;
import home.betiusage.entities.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hobby hobby;

    private LocalDateTime startDate;
    private Integer currentLevel = 1;
    private Integer totalXp = 0;
    private Integer currentStreak = 0; // Dage i træk
    private Integer longestStreak = 0;
    private LocalDate lastActivityDate;
    private Boolean isActive = true;

    @OneToMany(mappedBy = "tracking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goal> goals = new ArrayList<>();

    @OneToMany(mappedBy = "tracking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ActivityLog> activityLogs = new ArrayList<>();

    @Transient
    public Integer getCalculatedLevel() {
        return calculateLevel(this.totalXp);
    }

    @Transient
    public Integer getXpForNextLevel() {
        int currentLevelXp = calculateXpForLevel(this.currentLevel);
        int nextLevelXp = calculateXpForLevel(this.currentLevel + 1);
        return nextLevelXp - this.totalXp;
    }

    private Integer calculateLevel(Integer xp) {
        if (xp == null || xp < 0) return 1;
        return (xp / 100) + 1;
    }

    private Integer calculateXpForLevel(Integer level) {
        return (level - 1) * 100;
    }

    public Tracking(Long id) {
        this.id = id;
    }
}
