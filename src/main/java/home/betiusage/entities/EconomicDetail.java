package home.betiusage.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class EconomicDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private Double estimatedCost;
    private Double costRangeMin;
    private Double costRangeMax;
    private Boolean isRequired;
    private Boolean locationDependent;
    @Column(length = 500)
    private String comment;
    private String currency;
    private String purchaseLink;
    private String duration;
    @ManyToOne(fetch = FetchType.EAGER)
    private Hobby hobby;
}
