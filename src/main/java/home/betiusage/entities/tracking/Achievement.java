package home.betiusage.entities.tracking;

import home.betiusage.entities.Hobby;
import home.betiusage.enums.tracking.EAchievementType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String iconUrl;
    private Integer xpReward;

    @Enumerated(EnumType.STRING)
    private EAchievementType type;

    private Integer requirement;
    private Boolean isHobbySpecific = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hobby hobby;
}
