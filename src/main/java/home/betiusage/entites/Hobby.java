package home.betiusage.entites;

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
    @JoinTable(
            name = "hobby_category",
            joinColumns = @JoinColumn(name = "hobby_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();
    private String name;
    private String description;
    private String averageTimeConsumption;
    @OneToMany(fetch = FetchType.EAGER)
    private List<RequiredEquipment> requiredEquipment = new ArrayList<>();
    private Double minimumStartCapital;
    private Double averageCapital;
}
