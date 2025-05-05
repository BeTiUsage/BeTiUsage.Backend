package home.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDTO {
    private Long id;
    private String email;
    private String username;
    // TODO: Make sure this hobby list works everywhere
    private List<HobbyDTO> hobbyDTOList = new ArrayList<>();
}
