package home.betiusage.dto.tracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingDTO {
    private Long id;
    private Long profileId;
    private Long hobbyId;
    private String hobbyName;
    private LocalDateTime startDate;
    private Integer currentLevel;
    private Integer totalXp;
    private Integer currentStreak;
    private Integer longestStreak;
    private LocalDate lastActivityDate;
    private Boolean isActive;
    private Integer xpForNextLevel;
    private List<GoalDTO> activeGoals;
    private List<ActivityLogDTO> recentActivities;
}