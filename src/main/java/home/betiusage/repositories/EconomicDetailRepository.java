package home.betiusage.repositories;

import home.betiusage.entities.EconomicDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EconomicDetailRepository extends JpaRepository<EconomicDetail, Long> {
}
