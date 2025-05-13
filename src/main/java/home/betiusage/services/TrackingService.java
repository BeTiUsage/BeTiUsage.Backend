package home.betiusage.services;

import home.betiusage.dto.TrackingDTO;
import home.betiusage.entities.Goal;
import home.betiusage.entities.Tracking;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.repositories.TrackingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static home.betiusage.utils.ValidationUtils.existsById;
import static home.betiusage.utils.ValidationUtils.validateId;

@Service
public class TrackingService {
    private final TrackingRepository trackingRepository;

    public TrackingService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    public List<TrackingDTO> findAllByProfileId(Long profileId) {
        validateId(profileId, "profile");
        existsById(trackingRepository, profileId, "Profile");
        if (trackingRepository.findAllByProfile_Id(profileId).isEmpty()) {
            throw new NotFoundException("No tracking found for this profile");
        }
        return trackingRepository.findAllByProfile_Id(profileId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<TrackingDTO> findByIdAndProfileId(Long profileId, Long trackingId) {
        validateId(trackingId, "tracking");
        validateId(profileId, "profile");
        existsById(trackingRepository, trackingId, "tracking");
        existsById(trackingRepository, profileId, "profile");
        if (trackingRepository.findByIdAndProfile_Id(trackingId, profileId).isEmpty()) {
            throw new NotFoundException("No tracking found for this profile");
        }
        return trackingRepository.findByIdAndProfile_Id(trackingId, profileId)
                .map(this::toDTO);
    }


    public TrackingDTO updateTracking(TrackingDTO trackingDTO, Long id) {
        validateId(id, "tracking");
        existsById(trackingRepository, id, "tracking");
        Tracking existingTracking = trackingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tracking not found with id: " + id));

        if (trackingDTO.getMoneySpent() != null) {
            existingTracking.setMoneySpent(trackingDTO.getMoneySpent());
        }
        if (trackingDTO.getStartDate() != null) {
            existingTracking.setStartDate(trackingDTO.getStartDate());
        }

        if (trackingDTO.getGoalId() != null) {
            List<Goal> goals = trackingDTO.getGoalId().stream()
                    .map(goalId -> {
                        Goal goal = new Goal();
                        goal.setId(goalId);
                        return goal;
                    })
                    .toList();
            existingTracking.setGoals(goals);
        }

        if (trackingDTO.getHobbyId() != null) {
            existingTracking.getHobby().setId(trackingDTO.getHobbyId());
        }

        if (trackingDTO.getHobbyName() != null) {
            existingTracking.getHobby().setName(trackingDTO.getHobbyName());
        }

        return toDTO(trackingRepository.save(existingTracking));
    }

    public TrackingDTO toDTO (Tracking tracking) {
        TrackingDTO trackingDTO = new TrackingDTO();
        trackingDTO.setId(tracking.getId());
        trackingDTO.setHobbyId(tracking.getHobby().getId());
        trackingDTO.setHobbyName(tracking.getHobby().getName());
        trackingDTO.setGoalId(tracking.getGoals().stream().map(Goal::getId).toList());
        trackingDTO.setProfileId(tracking.getProfile().getId());
        trackingDTO.setMoneySpent(tracking.getMoneySpent());
        trackingDTO.setXp(tracking.getXp());
        trackingDTO.setStartDate(tracking.getStartDate());

        return trackingDTO;
    }
}
