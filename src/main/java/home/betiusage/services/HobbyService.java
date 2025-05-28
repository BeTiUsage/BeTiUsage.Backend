package home.betiusage.services;

import home.betiusage.dto.HobbyCategoryDTO;
import home.betiusage.dto.HobbyDTO;
import home.betiusage.dto.RequiredEquipmentDTO;
import home.betiusage.entities.Hobby;
import home.betiusage.repositories.CategoryRepository;
import home.betiusage.repositories.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HobbyService {
    HobbyRepository hobbyRepository;
    CategoryRepository categoryRepository;

    public HobbyService(HobbyRepository hobbyRepository, CategoryRepository categoryRepository) {
        this.hobbyRepository = hobbyRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<HobbyDTO> findAll() {
        return hobbyRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public HobbyDTO toDTO(Hobby hobby) {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(hobby.getId());
        hobbyDTO.setName(hobby.getName());
        hobbyDTO.setDescription(hobby.getDescription());
        hobbyDTO.setAverageCapital(hobby.getAverageCapital());
        hobbyDTO.setAverageTimeConsumption(hobby.getAverageTimeConsumption());
        hobbyDTO.setMinimumStartCapital(hobby.getMinimumStartCapital());
        hobbyDTO.setImg(hobby.getImg());
        hobbyDTO.setCostRating(
                hobby.getCostRating() != null
                        ? hobby.getCostRating()
                        : hobby.getCalculatedCostRating()
        );
        hobbyDTO.setRequiredEquipment(
                hobby.getRequiredEquipment()
                        .stream()
                        .map(equipment -> {
                            RequiredEquipmentDTO dto = new RequiredEquipmentDTO();
                            dto.setName(equipment.getName());
                            return dto;
                        })
                        .collect(Collectors.toList())
        );
        if (hobby.getCategories() != null) {
            List<HobbyCategoryDTO> hobbyCategoryDTOS = hobby.getCategories()
                    .stream()
                    .map(category -> {
                        HobbyCategoryDTO dto = new HobbyCategoryDTO();
                        dto.setName(category.getName());
                        dto.setSocial(category.getSocial());
                        return dto;
                    })
                    .collect(Collectors.toList());
            hobbyDTO.setCategories(hobbyCategoryDTOS);
        }
        return hobbyDTO;
    }
}