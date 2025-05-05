package home.betiusage.services;

import org.springframework.stereotype.Service;
import home.betiusage.dto.HobbyCategoryDto;
import home.betiusage.dto.HobbyDto;
import home.betiusage.dto.RequiredEquipmentDto;
import home.betiusage.entites.Hobby;
import home.betiusage.repositories.CategoryRepository;
import home.betiusage.repositories.HobbyRepository;
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

    public List<HobbyDto> findAll() {
        return hobbyRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public HobbyDto toDto(Hobby hobby) {
        HobbyDto hobbyDto = new HobbyDto();
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
                            RequiredEquipmentDto dto = new RequiredEquipmentDto();
                            dto.setName(equipment.getName());
                            return dto;
                        })
                        .collect(Collectors.toList())
        );
        if (hobby.getCategories() != null) {
            List<HobbyCategoryDto> hobbyCategoryDtos = hobby.getCategories()
                    .stream()
                    .map(category -> {
                        HobbyCategoryDto dto = new HobbyCategoryDto();
                        dto.setName(category.getName());
                        dto.setSocial(category.getSocial());
                        return dto;
                    })
                    .collect(Collectors.toList());
            hobbyDto.setCategories(hobbyCategoryDtos);
        }
        return hobbyDto;
    }
}