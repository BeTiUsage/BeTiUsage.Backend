package home.betiusage.services;

import home.betiusage.dto.*;
import home.betiusage.entities.*;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.errorHandling.exception.ValidationException;
import home.betiusage.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static home.betiusage.utils.ValidationUtils.existsById;
import static home.betiusage.utils.ValidationUtils.validateId;

@Service
public class TrackingService {
    private final TrackingRepository trackingRepository;
    private final HobbyRepository hobbyRepository;
    private final ProfileRepository profileRepository;
    private final GoalRepository goalRepository;
    private final SubGoalRepository subGoalRepository;
    private final GoalService goalService;

    public TrackingService(TrackingRepository trackingRepository, HobbyRepository hobbyRepository, ProfileRepository profileRepository, GoalRepository goalRepository, SubGoalRepository subGoalRepository, GoalService goalService) {
        this.trackingRepository = trackingRepository;
        this.hobbyRepository = hobbyRepository;
        this.profileRepository = profileRepository;
        this.goalRepository = goalRepository;
        this.subGoalRepository = subGoalRepository;
        this.goalService = goalService;
    }

    public List<TrackingResDTO> findAllByProfileId(Long profileId) {
        validateId(profileId, "profile");
        existsById(profileRepository, profileId, "Profile");
        if (trackingRepository.findAllByProfile_Id(profileId).isEmpty()) {
            throw new NotFoundException("No tracking found for this profile");
        }
        return trackingRepository.findAllByProfile_Id(profileId)
                .stream()
                .map(this::toResDTO)
                .toList();
    }

    public Optional<TrackingResDTO> findByIdAndProfileId(Long profileId, Long trackingId) {
        validateId(trackingId, "tracking");
        validateId(profileId, "profile");
        existsById(trackingRepository, trackingId, "tracking");
        existsById(profileRepository, profileId, "profile");
        if (trackingRepository.findByIdAndProfile_Id(trackingId, profileId).isEmpty()) {
            throw new NotFoundException("No tracking found for this profile");
        }
        return trackingRepository.findByIdAndProfile_Id(trackingId, profileId)
                .map(this::toResDTO);
    }

    public TrackingDTO createTracking(TrackingDTO trackingDTO) {
        if (trackingDTO.getProfileId() == null) {
            throw new ValidationException("Profile ID is required");
        }
        if (trackingDTO.getHobbyId() == null) {
            throw new ValidationException("Hobby ID is required");
        }

        Tracking tracking = new Tracking();

        tracking.setMoneySpent(trackingDTO.getMoneySpent());
        tracking.setXp(0);
        tracking.setStartDate(trackingDTO.getStartDate());

        Profile profile = profileRepository.findById(trackingDTO.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found with id: " + trackingDTO.getProfileId()));
        tracking.setProfile(profile);

        Hobby hobby = hobbyRepository.findById(trackingDTO.getHobbyId())
                .orElseThrow(() -> new NotFoundException("Hobby not found with id: " + trackingDTO.getHobbyId()));
        tracking.setHobby(hobby);

        Tracking savedTracking = trackingRepository.save(tracking);

        // Handle Goals if provided
        if (trackingDTO.getGoalId() != null && !trackingDTO.getGoalId().isEmpty()) {
            if (!goalService.areAnyGoalsTracked(trackingDTO.getGoalId())) {
                // First case: None of the goals are tracked, use existing goals
                List<Goal> goals = new ArrayList<>();

                for (Long goalId : trackingDTO.getGoalId()) {
                    Goal goal = goalRepository.findById(goalId)
                            .orElseThrow(() -> new NotFoundException("Goal not found with id: " + goalId));

                    // Set bidirectional relationship
                    goal.setIsTemplate(true);
                    goal.setTracking(savedTracking);
                    goals.add(goal);
                }

                savedTracking.setGoals(goals);
            } else {
                // Second case: At least one goal is tracked, clone the goals
                List<Goal> clonedGoals = new ArrayList<>();

                for (Long goalId : trackingDTO.getGoalId()) {
                    Goal originalGoal = goalRepository.findById(goalId)
                            .orElseThrow(() -> new NotFoundException("Goal not found with id: " + goalId));

                    originalGoal.setIsTemplate(false);
                    goalRepository.save(originalGoal);

                    Goal clonedGoal = new Goal();
                    clonedGoal.setName(originalGoal.getName());
                    clonedGoal.setGoalNumber(originalGoal.getGoalNumber());
                    clonedGoal.setHobbyName(originalGoal.getHobbyName());
                    clonedGoal.setCompleted(originalGoal.getCompleted());
                    clonedGoal.setIsTemplate(true);
                    clonedGoal.setTracking(savedTracking);

                    // Clone subgoals if present
                    if (originalGoal.getSubGoals() != null && !originalGoal.getSubGoals().isEmpty()) {
                        List<SubGoal> clonedSubGoals = new ArrayList<>();
                        for (SubGoal subGoal : originalGoal.getSubGoals()) {
                            SubGoal clonedSubGoal = new SubGoal();
                            clonedSubGoal.setName(subGoal.getName());
                            clonedSubGoal.setCompleted(subGoal.getCompleted());
                            clonedSubGoal.setGoal(clonedGoal);
                            clonedSubGoals.add(clonedSubGoal);
                        }
                        clonedGoal.setSubGoals(clonedSubGoals);
                    }

                    // Save the cloned goal
                    Goal savedClonedGoal = goalRepository.save(clonedGoal);
                    clonedGoals.add(savedClonedGoal);
                }

                savedTracking.setGoals(clonedGoals);
            }

            // Save the tracking with its associated goals
            savedTracking = trackingRepository.save(savedTracking);
        }

        return toDTO(savedTracking);
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

    public TrackingDTO deleteTracking(Long id) {
        Tracking tracking = trackingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tracking not found with id: " + id));

        TrackingDTO deletedProfileDTO = toDTO(tracking);

        tracking.setProfile(null);
        tracking.setHobby(null);

        // Break the bi-directional relationship between tracking and goals
        for (Goal goal : tracking.getGoals()) {
            goal.setTracking(null);
        }

        tracking.getGoals().clear();
        // To make sure there are no relationships left when trying to delete
        trackingRepository.save(tracking);

        trackingRepository.delete(tracking);

        return deletedProfileDTO;
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

    public TrackingResDTO toResDTO(Tracking tracking) {
        TrackingResDTO trackingResDTO = new TrackingResDTO();
        trackingResDTO.setId(tracking.getId());
        trackingResDTO.setHobbyId(tracking.getHobby().getId());
        trackingResDTO.setHobbyName(tracking.getHobby().getName());
        trackingResDTO.setGoals(tracking.getGoals().stream().map(goal -> {
            GoalDTO goalDTO = new GoalDTO();
            goalDTO.setId(goal.getId());
            goalDTO.setName(goal.getName());
            goalDTO.setCompleted(goal.getCompleted());
            goalDTO.setTrackingId(goal.getTracking() != null ? goal.getTracking().getId() : null);
            goalDTO.setGoalNumber(goal.getGoalNumber());
            goalDTO.setSubGoals(goal.getSubGoals().stream().map(subGoal -> {
                SubGoalDTO subGoalDTO = new SubGoalDTO();
                subGoalDTO.setId(subGoal.getId());
                subGoalDTO.setName(subGoal.getName());
                subGoalDTO.setCompleted(subGoal.getCompleted());
                return subGoalDTO;
            }).toList());
            return goalDTO;
        }).toList());
        trackingResDTO.setProfileId(tracking.getProfile().getId());
        trackingResDTO.setMoneySpent(tracking.getMoneySpent());
        trackingResDTO.setXp(tracking.getXp());
        trackingResDTO.setStartDate(tracking.getStartDate());
        trackingResDTO.setImg(tracking.getHobby().getImg());

        return trackingResDTO;
    }
}
