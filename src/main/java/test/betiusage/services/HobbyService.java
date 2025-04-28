package test.betiusage.services;

import org.springframework.stereotype.Service;
import test.betiusage.dto.HobbyDto;
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
        hobbyDto.setCategories(hobby.getCategories());
        hobbyDto.setName(hobby.getName());
        hobbyDto.setDescription(hobby.getDescription());
        hobbyDto.setAverageCapital(hobby.getAverageCapital());
        hobbyDto.setAverageTimeConsumption(hobby.getAverageTimeConsumption());
        hobbyDto.setMinimumStartCapital(hobby.getMinimumStartCapital());
        hobbyDto.setRequiredEquipment(hobby.getRequiredEquipment());

        return hobbyDto;
    }
}
