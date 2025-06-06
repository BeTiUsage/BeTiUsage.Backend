package home.betiusage.dto;

import home.betiusage.enums.ECostRating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HobbyDTO {
    private Long id;
    private List<CategoryDTO> categories;
    private String name;
    private String description;
    private String averageTimeConsumption;
    private Double minimumStartCapital;
    private Double averageStartCapital;
    private ECostRating costRating;
    private String img;
}

