package home.betiusage.services;

import home.betiusage.repositories.CommunityRepository;
import org.springframework.stereotype.Service;
import home.betiusage.dto.CommunityDTO;
import home.betiusage.entities.Community;
import home.betiusage.repositories.HobbyRepository;
import java.util.List;
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

    public CommunityDTO toDTO(Community Community) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setId(Community.getId());
        communityDTO.setUrl(Community.getUrl());
        communityDTO.setDescription(Community.getDescription());
        communityDTO.setHobbyId(Community.getHobby().getId());
        communityDTO.setHobbyName(Community.getHobby().getName());
        communityDTO.setForumName(Community.getForumName());

        return communityDTO;
    }
}
