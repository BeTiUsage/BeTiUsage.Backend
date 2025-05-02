package test.betiusage.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import test.betiusage.entitys.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
