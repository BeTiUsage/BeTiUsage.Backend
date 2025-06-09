package home.betiusage.services;

import home.betiusage.dto.CategoryDTO;
import home.betiusage.entities.Category;
import home.betiusage.entities.Hobby;
import home.betiusage.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(this::toDTO).toList();
    }

    public CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setSocial(category.getSocial());
        categoryDto.setHobbyName(category.getHobbies().stream().map(Hobby::getName).collect(Collectors.joining(", ")));

        return categoryDto;
    }
}
