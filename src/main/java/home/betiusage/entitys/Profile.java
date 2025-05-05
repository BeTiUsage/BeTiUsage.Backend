
package home.betiusage.entitys;

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
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Hobby> hobbies = new ArrayList<>();
    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER) //cascade = CascadeType.ALL here?
    private List<Tracking> trackings = new ArrayList<>();

    public Profile(String username, String email) {
        this.username = username;
        this.email = email;
    }
}