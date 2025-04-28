package test.betiusage.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Category> categories;
    private String name;
    private String description;
    private String averageTimeConsumption;
    private ArrayList<String> requiredEquipment;
    private Double minimumStartCapital;
    private Double averageCapital;
}
