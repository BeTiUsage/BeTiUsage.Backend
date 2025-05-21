package home.betiusage.dto;

import home.betiusage.enums.ECostRating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HobbyDTO {
    private Long id;
    private List<HobbyCategoryDTO> categories;
    private String name;
    private String description;
    private String averageTimeConsumption;
    private List<RequiredEquipmentDTO> requiredEquipment = new ArrayList<>();
    private Double minimumStartCapital;
    private Double averageCapital;
    private ECostRating costRating;
    private String img;
}

