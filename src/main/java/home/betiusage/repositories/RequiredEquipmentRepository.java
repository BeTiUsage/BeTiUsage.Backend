package home.betiusage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import home.betiusage.entities.RequiredEquipment;

public interface RequiredEquipmentRepository extends JpaRepository<RequiredEquipment, Long> {
}
