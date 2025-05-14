package home.betiusage.services;

import home.betiusage.dto.SubGoalDTO;
import home.betiusage.entities.SubGoal;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.repositories.SubGoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubGoalService {
    private final SubGoalRepository subGoalRepository;

    public SubGoalService(SubGoalRepository subGoalRepository) {
        this.subGoalRepository = subGoalRepository;
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
        if (subGoalDTO.getCompleted() != null) {
            existingSubGoal.setCompleted(subGoalDTO.getCompleted());
        }

        return ToDTO(subGoalRepository.save(ToEntity(subGoalDTO)));
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
        return subGoal;
    }


}
