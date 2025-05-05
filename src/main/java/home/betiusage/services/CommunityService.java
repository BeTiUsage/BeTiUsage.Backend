package home.betiusage.services;

import home.betiusage.repositories.CommunityRepository;
import org.springframework.stereotype.Service;
import home.betiusage.dto.CommunityDTO;
import home.betiusage.entites.Community;
import home.betiusage.repositories.HobbyRepository;
import home.betiusage.utils.ValidationUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunityService {
    public CommunityRepository communityRepository;
    public HobbyRepository hobbyRepository;

    public CommunityService(CommunityRepository communityRepository, HobbyRepository hobbyRepository) {
        this.communityRepository = communityRepository;
        this.hobbyRepository = hobbyRepository;
    }

    public List<CommunityDTO> findAll() {
        return communityRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CommunityDTO> findById(Long communityId) {

        ValidationUtils.validateId(communityId, "communityId");
        ValidationUtils.existsById(communityRepository, communityId, "communityId");

        return communityRepository.findById(communityId).map(this::toDTO);
    }

    public List<CommunityDTO> findByHobbyId(Long hobbyId) {

        ValidationUtils.validateId(hobbyId, "hobbyId");
        ValidationUtils.existsById(hobbyRepository, hobbyId, "hobbyId");

        return communityRepository.findByHobbyId(hobbyId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CommunityDTO toDTO(Community Community) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setId(Community.getId());
        communityDTO.setUrl(Community.getUrl());
        communityDTO.setDescription(Community.getDescription());
        communityDTO.setHobbyId(Community.getHobby().getId());
        communityDTO.setHobbyName(Community.getHobby().getName());
        return communityDTO;
    }
}
