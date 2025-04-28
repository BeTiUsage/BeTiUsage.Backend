package test.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.betiusage.entitys.Category;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class HobbyDto {
    private Long id;
    private Category categoryId;
    private String name;
    private String description;
    private String averageTimeConsumption;
    private ArrayList<String> requiredEquipment;
    private Double minimumStartCapital;
    private Double averageCapital;
}
