package home.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import home.betiusage.entites.Hobby;

@Getter
@Setter
@NoArgsConstructor
public class CommunityDTO {
    private Long id;
    private String url;
    private String description;
    private Long hobbyId;
    private String hobbyName;
}
