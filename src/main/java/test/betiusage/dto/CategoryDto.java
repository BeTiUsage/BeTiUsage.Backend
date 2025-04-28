package test.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.betiusage.entitys.Hobby;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private Hobby hobbyId;
    private String name;
    private Boolean social;
}
