package home.betiusage.services;

import home.betiusage.dto.HobbyCategoryDTO;
import home.betiusage.dto.HobbyDTO;
import home.betiusage.dto.RequiredEquipmentDTO;
import home.betiusage.entities.Category;
import home.betiusage.entities.Hobby;
import home.betiusage.entities.RequiredEquipment;
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
        return hobbyRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public HobbyDTO toDto(Hobby hobby) {
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

    public Hobby toEntity(HobbyDTO hobbyDTO) {
        Hobby hobby = new Hobby();
        hobby.setId(hobbyDTO.getId());
        hobby.setName(hobbyDTO.getName());
        hobby.setDescription(hobbyDTO.getDescription());
        hobby.setAverageCapital(hobbyDTO.getAverageCapital());
        hobby.setAverageTimeConsumption(hobbyDTO.getAverageTimeConsumption());
        hobby.setMinimumStartCapital(hobbyDTO.getMinimumStartCapital());
        hobby.setImg(hobbyDTO.getImg());

        if (hobbyDTO.getRequiredEquipment() != null) {
            hobby.setRequiredEquipment(
                    hobbyDTO.getRequiredEquipment().stream()
                            .map(equipmentDTO -> {
                                RequiredEquipment equipment = new RequiredEquipment();
                                equipment.setName(equipmentDTO.getName());
                                return equipment;
                            })
                            .collect(Collectors.toList())
            );
        }

        if (hobbyDTO.getCategories() != null) {
            hobby.setCategories(
                    hobbyDTO.getCategories().stream()
                            .map(categoryDTO -> {
                                Category category = new Category();
                                category.setName(categoryDTO.getName());
                                category.setSocial(categoryDTO.getSocial());
                                return category;
                            })
                            .collect(Collectors.toList())
            );
        }

        return hobby;
    }
}