package test.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.betiusage.entitys.Hobby;

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
