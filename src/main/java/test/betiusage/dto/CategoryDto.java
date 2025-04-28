package test.betiusage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.betiusage.entitys.Hobby;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String hobbyName;
    private String name;
    private Boolean social;
}
