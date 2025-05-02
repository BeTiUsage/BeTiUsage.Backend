package test.betiusage.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import test.betiusage.entitys.RequiredEquipment;

public interface RequiredEquipmentRepository extends JpaRepository<RequiredEquipment, Long> {
}
