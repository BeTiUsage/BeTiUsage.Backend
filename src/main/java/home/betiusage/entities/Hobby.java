package home.betiusage.entities;

import home.betiusage.enums.ECostRating;
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
    private String img;
    //economic
    @Enumerated(EnumType.STRING)
    private ECostRating costRating;
    private Double minimumStartCapital;
    private Double averageStartCapital;
    @OneToMany(fetch = FetchType.EAGER)
    private List<EconomicDetail> economicDetails = new ArrayList<>();

    public void setAverageStartCapital(Double averageCapital) {
        this.averageStartCapital = averageCapital;
        this.costRating = calculateCostRating(averageCapital);
    }

    @Transient
    public ECostRating getCalculatedCostRating() {
        return calculateCostRating(this.averageStartCapital);
    }

    private ECostRating calculateCostRating(Double capital) {
        if (capital == null) return null;

        if (capital < 50) {
            return ECostRating.VERY_CHEAP;
        } else if (capital < 100) {
            return ECostRating.CHEAP;
        } else if (capital < 200) {
            return ECostRating.MODERATE;
        } else if (capital < 500) {
            return ECostRating.EXPENSIVE;
        } else {
            return ECostRating.VERY_EXPENSIVE;
        }
    }
}