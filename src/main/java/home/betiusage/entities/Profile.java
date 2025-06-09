
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
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Tracking> trackings = new ArrayList<>();
    @Column(nullable = false, unique = true)
    private String clerkId;

}