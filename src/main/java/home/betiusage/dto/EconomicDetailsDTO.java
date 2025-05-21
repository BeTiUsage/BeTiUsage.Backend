package home.betiusage.dto;

import home.betiusage.enums.ECostRating;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EconomicDetailsDTO {
    private Long id;
    private Long hobbyId;
    private ECostRating costRating;
    private Double minimumStartCapital;
    private Double averageCapital;
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
}
