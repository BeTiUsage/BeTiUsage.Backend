package home.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TrackingDTO {
    private Long id;
    private Long profileId;
    private List<Long> goalId;
    private Long hobbyId;
    private String hobbyName;
    private Double moneySpent;
    private Integer xp;
    private LocalDateTime startDate;
    private String hobbyImg;
}
