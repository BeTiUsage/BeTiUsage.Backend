package test.betiusage.services;

import org.springframework.stereotype.Service;
import test.betiusage.dto.CategoryDto;
import test.betiusage.dto.HobbyCategoryDto;
import test.betiusage.dto.HobbyDto;
import test.betiusage.dto.RequiredEquipmentDto;
import test.betiusage.entitys.Hobby;
import test.betiusage.repositorys.CategoryRepository;
import test.betiusage.repositorys.HobbyRepository;

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