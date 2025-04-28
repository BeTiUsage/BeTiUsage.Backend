package test.betiusage.services;

import org.springframework.stereotype.Service;
import test.betiusage.dto.CategoryDto;
import test.betiusage.entitys.Category;
import test.betiusage.repositorys.CategoryRepository;

import java.util.List;

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
        categoryDto.setHobbies(category.getHobbies());
        categoryDto.setName(category.getName());
        categoryDto.setSocial(category.getSocial());

        return categoryDto;
    }
}
