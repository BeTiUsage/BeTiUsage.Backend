package home.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
}
