package home.betiusage.services;

import home.betiusage.dto.SubGoalDTO;
import home.betiusage.entities.Goal;
import home.betiusage.entities.SubGoal;
import home.betiusage.entities.Tracking;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.repositories.SubGoalRepository;
import home.betiusage.repositories.TrackingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubGoalService {
    private final SubGoalRepository subGoalRepository;
    private final TrackingRepository trackingRepository;

    public SubGoalService(SubGoalRepository subGoalRepository, TrackingRepository trackingRepository) {
        this.subGoalRepository = subGoalRepository;
        this.trackingRepository = trackingRepository;
    }

    public List<SubGoalDTO> findAll() {
        return subGoalRepository.findAll().stream().map(this::ToDTO).toList();
    }

    public SubGoalDTO createSubGoal(SubGoalDTO subGoalDTO) {

        if (subGoalDTO.getId() != null) {
            throw new NotFoundException("ID should not be provided for a new SubGoal");
        }
        if (subGoalDTO.getName() == null || subGoalDTO.getName().isEmpty()) {
            throw new NotFoundException("SubGoal name should not be null or empty");
        }

        if (subGoalDTO.getCompleted() == null) {
            throw new NotFoundException("SubGoal completed status should not be null");
        }
        return ToDTO(subGoalRepository.save(ToEntity(subGoalDTO)));
    }

    public SubGoalDTO updateSubGoal(SubGoalDTO subGoalDTO, Long id) {
        if (id == null) {
            throw new NotFoundException("ID should not be null");
        }

        SubGoal existingSubGoal = subGoalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubGoal not found with id: " + id));

        if (subGoalDTO.getName() != null) {
            existingSubGoal.setName(subGoalDTO.getName());
        }

        return ToDTO(subGoalRepository.save(ToEntity(subGoalDTO)));
    }

    public SubGoalDTO updateSubGoalCompletedStatus(SubGoalDTO subGoalDTO, Long id) {
        if (id == null) {
            throw new NotFoundException("ID should not be null");
        }

        SubGoal existingSubGoal = subGoalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubGoal not found with id: " + id));

        if (subGoalDTO.getCompleted() != null) {
            existingSubGoal.setCompleted(subGoalDTO.getCompleted());
            if (subGoalDTO.getCompleted()) {
                existingSubGoal.setCompleted(true);
                awardXpForSubgoalCompletion(existingSubGoal);
            } else {
                existingSubGoal.setCompleted(false);
            }
        }

        return ToDTO(subGoalRepository.save(ToEntity(subGoalDTO)));
    }

    private void awardXpForSubgoalCompletion(SubGoal subGoal) {
        Goal parentGoal = subGoal.getGoal();
        if (parentGoal == null || parentGoal.getTracking() == null) {
            return; // No tracking to update
        }

        Tracking tracking = parentGoal.getTracking();

        // Initialize XP if it's null
        if (tracking.getXp() == null) {
            tracking.setXp(0);
        }

        // Award XP for subgoal completion
        tracking.setXp(tracking.getXp() + XpService.getSubgoalCompletionXp());

        // Save the tracking with updated XP
        trackingRepository.save(tracking);
    }



    public SubGoalDTO deleteSubGoal(Long id) {
        if (id == null) {
            throw new NotFoundException("ID should not be null");
        }

        SubGoal existingSubGoal = subGoalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubGoal not found with id: " + id));

        subGoalRepository.delete(existingSubGoal);
        return ToDTO(existingSubGoal);
    }

    public SubGoalDTO ToDTO (SubGoal subGoal) {
        SubGoalDTO subGoalDTO = new SubGoalDTO();
        subGoalDTO.setId(subGoal.getId());
        subGoalDTO.setName(subGoal.getName());
        subGoalDTO.setCompleted(subGoal.getCompleted());
        return subGoalDTO;
    }

    public SubGoal ToEntity (SubGoalDTO subGoalDTO) {
        SubGoal subGoal = new SubGoal();
        subGoal.setId(subGoalDTO.getId());
        subGoal.setName(subGoalDTO.getName());
        subGoal.setCompleted(subGoalDTO.getCompleted());

        if (subGoalDTO.getId() != null) {
            SubGoal existingSubGoal = subGoalRepository.findById(subGoalDTO.getId()).orElse(null);
            if (existingSubGoal != null && existingSubGoal.getGoal() != null) {
                subGoal.setGoal(existingSubGoal.getGoal());
            }
        }
        return subGoal;
    }


}
