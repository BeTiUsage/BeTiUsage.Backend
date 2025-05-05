package home.betiusage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import home.betiusage.entites.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
