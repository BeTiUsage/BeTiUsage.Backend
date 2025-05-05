package home.betiusage.services;

import home.betiusage.entitys.Hobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import home.betiusage.dto.ProfileDTO;
import home.betiusage.entitys.Profile;
import home.betiusage.repositorys.ProfileRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static home.betiusage.util.ValidationUtils.*;

@Service
public class ProfileService {
    ProfileRepository profileRepository;
    @Autowired
    private HobbyService hobbyService;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileDTO> findAll() {
        return profileRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ProfileDTO> findProfile(Long id) {
        validateId(id, "Profile");
        existsById(profileRepository, id, "Profile");

        return profileRepository.findById(id).map(this::toDTO);
    }

    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        // TODO: Make a check to make sure it's actually saved
        Profile profile = toEntity(profileDTO);
        profileRepository.save(profile);
        return toDTO(profile);
    }

    public ProfileDTO updateProfile(ProfileDTO profileDTO, Long id) {
        Profile profileToUpdate = getOrThrow(profileRepository, id, "Profile");

        if (profileDTO.getHobbyDTOList() != null && !profileDTO.getHobbyDTOList().isEmpty()) {
            List<Hobby> updatedHobbies = profileDTO.getHobbyDTOList().stream()
                    .map(hobbyService::toEntity) // convert DTO to entity
                    .collect(Collectors.toList());

            profileToUpdate.setHobbies(updatedHobbies);
        }

        // TODO: This can override it if an empty string is sent alon, do validation for that
        if (profileDTO.getUsername() != null) {
            profileToUpdate.setUsername(profileDTO.getUsername());
        }

        profileRepository.save(profileToUpdate);

        return toDTO(profileToUpdate);
    }

    private ProfileDTO toDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setUsername(profile.getUsername());
        profileDTO.setHobbyDTOList(profile
                .getHobbies()
                .stream().map(hobbyService::toDto)
                .collect(Collectors.toList()));

        return profileDTO;
    }

    private Profile toEntity(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setEmail(profileDTO.getEmail());
        profile.setUsername(profileDTO.getUsername());
        profile.setHobbies(profileDTO
                .getHobbyDTOList()
                .stream().map(hobbyService::toEntity)
                .collect(Collectors.toList()));

        return profile;
    }
}