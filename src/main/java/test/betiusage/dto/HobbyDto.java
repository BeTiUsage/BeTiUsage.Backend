package test.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HobbyDto {
    private Long id;
    private List<HobbyCategoryDto> categories;
    private String name;
    private String description;
    private String averageTimeConsumption;
    private RequiredEquipmentDto requiredEquipment;
    private Double minimumStartCapital;
    private Double averageCapital;
}
