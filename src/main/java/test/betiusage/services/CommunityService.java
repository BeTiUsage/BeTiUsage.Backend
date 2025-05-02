package test.betiusage.services;

import org.springframework.stereotype.Service;
import test.betiusage.dto.CommunityDTO;
import test.betiusage.entitys.Community;
import test.betiusage.errorhandeling.exception.NotFoundException;
import test.betiusage.errorhandeling.exception.ValidationException;
import test.betiusage.repositorys.CommunityRepository;
import test.betiusage.repositorys.HobbyRepository;

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
        if (communityId == null || communityId <= 0) {
            throw new ValidationException("Please provide a valid communityId id");
        }

        if (!communityRepository.existsById(communityId)) {
            throw new NotFoundException("community with id " + communityId + " does not exist");
        }

        return communityRepository.findById(communityId).map(this::toDTO);
    }

    public List<CommunityDTO> findByHobbyId(Long hobbyId) {
        if (hobbyId == null || hobbyId <= 0) {
            throw new ValidationException("Please provide a valid hobby id");
        }

        if (!hobbyRepository.existsById(hobbyId)) {
            throw new NotFoundException("Hobby with id " + hobbyId + " does not exist");
        }

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
