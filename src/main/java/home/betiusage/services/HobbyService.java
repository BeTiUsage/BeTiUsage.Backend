package home.betiusage.services;

import home.betiusage.dto.CategoryDTO;
import home.betiusage.dto.HobbyDTO;
import home.betiusage.entities.Hobby;
import home.betiusage.repositories.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HobbyService {
    HobbyRepository hobbyRepository;

    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }

    public List<HobbyDTO> findAll() {
        return hobbyRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public HobbyDTO toDTO(Hobby hobby) {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(hobby.getId());
        hobbyDTO.setName(hobby.getName());
        hobbyDTO.setDescription(hobby.getDescription());
        hobbyDTO.setAverageStartCapital(hobby.getAverageStartCapital());
        hobbyDTO.setAverageTimeConsumption(hobby.getAverageTimeConsumption());
        hobbyDTO.setMinimumStartCapital(hobby.getMinimumStartCapital());
        hobbyDTO.setImg(hobby.getImg());
        hobbyDTO.setCostRating(
                hobby.getCostRating() != null
                        ? hobby.getCostRating()
                        : hobby.getCalculatedCostRating()
        );
        if (hobby.getCategories() != null) {
            List<CategoryDTO> hobbyCategoryDTOS = hobby.getCategories()
                    .stream()
                    .map(category -> {
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setName(category.getName());
                        categoryDTO.setHobbyName(category.getHobbies().stream().map(Hobby::getName).collect(Collectors.joining(", ")));
                        categoryDTO.setId(category.getId());
                        categoryDTO.setSocial(category.getSocial());
                        return categoryDTO;
                    })
                    .collect(Collectors.toList());
            hobbyDTO.setCategories(hobbyCategoryDTOS);
        }
        return hobbyDTO;
    }
}