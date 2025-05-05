package home.betiusage.services;

import home.betiusage.dto.CategoryDTO;
import home.betiusage.entitys.Category;
import home.betiusage.entitys.Hobby;
import home.betiusage.repositorys.CategoryRepository;
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
        return categoryRepository.findAll().stream().map(this::toDto).toList();
    }

    public CategoryDTO toDto(Category category) {
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setSocial(category.getSocial());
        categoryDto.setHobbyName(category.getHobbies().stream().map(Hobby::getName).collect(Collectors.joining(", ")));

        return categoryDto;
    }
}
