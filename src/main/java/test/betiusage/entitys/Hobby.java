package test.betiusage.entitys;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Category categoryId;
    private String name;
    private String description;
    private String averageTimeConsumption;
    private ArrayList<String> requiredEquipment;
    private Double minimumStartCapital;
    private Double averageCapital;
}
