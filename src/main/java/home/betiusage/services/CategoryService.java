package home.betiusage.services;

import org.springframework.stereotype.Service;
import home.betiusage.dto.CategoryDto;
import home.betiusage.entites.Category;
import home.betiusage.entites.Hobby;
import home.betiusage.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(this::toDto).toList();
    }

    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setSocial(category.getSocial());
        categoryDto.setHobbyName(category.getHobbies().stream().map(Hobby::getName).collect(Collectors.joining(", ")));

        return categoryDto;
    }
}
