package home.betiusage.services;

import home.betiusage.entities.Hobby;
import home.betiusage.errorhandeling.exception.NotFoundException;
import home.betiusage.errorhandeling.exception.ValidationException;
import org.springframework.stereotype.Service;
import home.betiusage.dto.ProfileDTO;
import home.betiusage.entities.Profile;
import home.betiusage.repositories.ProfileRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static home.betiusage.util.ValidationUtils.*;

@Service
public class ProfileService {
    ProfileRepository profileRepository;
    HobbyService hobbyService;

    public ProfileService(ProfileRepository profileRepository, HobbyService hobbyService) {
        this.profileRepository = profileRepository;
        this.hobbyService = hobbyService;
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
        validateProfileDTO(profileDTO);
        Profile profile = toEntity(profileDTO);
        profileRepository.save(profile);

        if (profileRepository.findById(profileDTO.getId()) == null)
            throw new NotFoundException("Profile wasn't created or saved properly");

        return toDTO(profile);
    }

    public ProfileDTO updateProfile(ProfileDTO profileDTO, Long id) {
        existsById(profileRepository, id, "Profile");
        Profile profileToUpdate = profileRepository.findById(id).get();
        validateProfileDTO(profileDTO);

        if (profileDTO.getHobbyDTOList() != null && !profileDTO.getHobbyDTOList().isEmpty()) {
            List<Hobby> updatedHobbies = profileDTO.getHobbyDTOList().stream()
                    .map(hobbyService::toEntity)
                    .collect(Collectors.toList());

            profileToUpdate.setHobbies(updatedHobbies);
        }

        if (profileDTO.getUsername() != null && !profileDTO.getUsername().equals("")) {
            profileToUpdate.setUsername(profileDTO.getUsername());
        }

        profileRepository.save(profileToUpdate);

        return toDTO(profileToUpdate);
    }

    public ProfileDTO deleteProfile(Long id) {
        existsById(profileRepository, id, "Profile");
        ProfileDTO profileToDelete = toDTO(profileRepository.findById(id).get());
        profileRepository.deleteById(id);

        return profileToDelete;
    }

    public ProfileDTO validateProfileDTO(ProfileDTO profileDTO) {
        if (profileDTO.getUsername() == null || profileDTO.getUsername().equals(""))
            throw new ValidationException("You have to write a username");

        // We shouldn't check that it exists here right? We're just checking the profileDTO is correct?
//        profileRepository.findById(profileDTO.getId()).orElseThrow(()
//                -> new NotFoundException("Profile with ID: " + profileDTO.getId() + "does not exist"));

        return profileDTO;
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