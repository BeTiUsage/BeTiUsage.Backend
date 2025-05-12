package home.betiusage.repositories;

import home.betiusage.dto.TrackingDTO;
import home.betiusage.entities.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    List<Tracking> findAllByProfile_Id(Long profileId);
    Optional<Tracking> findByIdAndProfile_Id(Long trackingId, Long profileId);
}
