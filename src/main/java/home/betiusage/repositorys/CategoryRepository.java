package home.betiusage.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import home.betiusage.entitys.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
