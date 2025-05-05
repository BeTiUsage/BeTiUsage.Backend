package home.betiusage.services;

import home.betiusage.dto.HobbyCategoryDTO;
import home.betiusage.dto.HobbyDTO;
import home.betiusage.dto.RequiredEquipmentDTO;
import home.betiusage.entitys.Category;
import home.betiusage.entitys.Hobby;
import home.betiusage.entitys.RequiredEquipment;
import home.betiusage.repositorys.CategoryRepository;
import home.betiusage.repositorys.HobbyRepository;
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
        HobbyDTO hobbyDto = new HobbyDTO();
        hobbyDto.setId(hobby.getId());
        hobbyDto.setName(hobby.getName());
        hobbyDto.setDescription(hobby.getDescription());
        hobbyDto.setAverageCapital(hobby.getAverageCapital());
        hobbyDto.setAverageTimeConsumption(hobby.getAverageTimeConsumption());
        hobbyDto.setMinimumStartCapital(hobby.getMinimumStartCapital());
        hobbyDto.setRequiredEquipment(
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
            hobbyDto.setCategories(hobbyCategoryDTOS);
        }
        return hobbyDto;
    }

    public Hobby toEntity(HobbyDTO dto) {
        Hobby hobby = new Hobby();
        hobby.setId(dto.getId());
        hobby.setName(dto.getName());
        hobby.setDescription(dto.getDescription());
        hobby.setAverageCapital(dto.getAverageCapital());
        hobby.setAverageTimeConsumption(dto.getAverageTimeConsumption());
        hobby.setMinimumStartCapital(dto.getMinimumStartCapital());

        if (dto.getRequiredEquipment() != null) {
            hobby.setRequiredEquipment(
                    dto.getRequiredEquipment().stream()
                            .map(equipmentDTO -> {
                                RequiredEquipment equipment = new RequiredEquipment();
                                equipment.setName(equipmentDTO.getName());
                                return equipment;
                            })
                            .collect(Collectors.toList())
            );
        }

        if (dto.getCategories() != null) {
            hobby.setCategories(
                    dto.getCategories().stream()
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