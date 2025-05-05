package home.betiusage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import home.betiusage.entites.RequiredEquipment;

public interface RequiredEquipmentRepository extends JpaRepository<RequiredEquipment, Long> {
}
