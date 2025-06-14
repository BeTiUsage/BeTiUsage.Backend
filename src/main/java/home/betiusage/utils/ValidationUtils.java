package home.betiusage.utils;

import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.errorHandling.exception.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;

public class ValidationUtils {
    public static void validateId(Long id, String fieldName) {
        if (id == null || id <= 0) {
            throw new ValidationException("Please provide a valid " + fieldName + " id");
        }
    }

    public static void existsById(JpaRepository<?, Long> repository, Long id, String entityName) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(entityName + " with id " + id + " does not exist");
        }
    }
}
