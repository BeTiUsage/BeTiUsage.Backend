package home.betiusage.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import home.betiusage.entitys.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
