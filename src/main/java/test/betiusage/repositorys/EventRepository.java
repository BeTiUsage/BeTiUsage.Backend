package test.betiusage.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.betiusage.entitys.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
