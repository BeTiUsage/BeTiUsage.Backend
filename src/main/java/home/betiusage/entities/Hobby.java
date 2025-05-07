package home.betiusage.entities;

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
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();
    private String name;
    private String description;
    private String averageTimeConsumption;
    @OneToMany(mappedBy = "hobby", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequiredEquipment> requiredEquipment = new ArrayList<>();
    private Double minimumStartCapital;
    private Double averageCapital;
    private String img;
}