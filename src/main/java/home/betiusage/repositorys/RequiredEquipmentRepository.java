package home.betiusage.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import home.betiusage.entitys.RequiredEquipment;

public interface RequiredEquipmentRepository extends JpaRepository<RequiredEquipment, Long> {
}
