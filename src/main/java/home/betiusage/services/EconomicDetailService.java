package home.betiusage.services;

import home.betiusage.dto.*;
import home.betiusage.entities.EconomicDetail;
import home.betiusage.entities.Hobby;
import home.betiusage.repositories.EconomicDetailRepository;
import home.betiusage.repositories.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EconomicDetailService {
    EconomicDetailRepository economicDetailRepository;

    public EconomicDetailService(EconomicDetailRepository economicDetailRepository, HobbyRepository hobbyRepository) {
        this.economicDetailRepository = economicDetailRepository;
    }

    public List<EconomicDetailsDTO> getEconomicDetails() {
        return economicDetailRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EconomicDetailsDTO toDTO(EconomicDetail economicDetail) {
        EconomicDetailsDTO dto = new EconomicDetailsDTO();

        dto.setId(economicDetail.getId());
        dto.setLabel(economicDetail.getLabel());
        dto.setEstimatedCost(economicDetail.getEstimatedCost());
        dto.setCostRangeMin(economicDetail.getCostRangeMin());
        dto.setCostRangeMax(economicDetail.getCostRangeMax());
        dto.setIsRequired(economicDetail.getIsRequired());
        dto.setLocationDependent(economicDetail.getLocationDependent());
        dto.setComment(economicDetail.getComment());
        dto.setCurrency(economicDetail.getCurrency());
        dto.setPurchaseLink(economicDetail.getPurchaseLink());
        dto.setDuration(economicDetail.getDuration());

        if (economicDetail.getHobby() != null) {
            Hobby hobby = economicDetail.getHobby();

            dto.setHobbyId(hobby.getId());
            dto.setAverageCapital(hobby.getAverageStartCapital());
            dto.setMinimumStartCapital(hobby.getMinimumStartCapital());

            if (hobby.getCostRating() != null) {
                dto.setCostRating(hobby.getCostRating());
            } else {
                dto.setCostRating(hobby.getCalculatedCostRating());
            }
        }
        return dto;
    }
}
