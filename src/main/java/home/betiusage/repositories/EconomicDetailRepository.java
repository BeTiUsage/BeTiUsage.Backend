package home.betiusage.repositories;

import home.betiusage.entities.EconomicDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EconomicDetailRepository extends JpaRepository<EconomicDetail, Long> {
    List<EconomicDetail> findAllByHobbyId(Long id);
}
