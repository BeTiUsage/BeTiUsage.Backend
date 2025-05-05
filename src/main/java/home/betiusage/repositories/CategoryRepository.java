package home.betiusage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import home.betiusage.entites.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
