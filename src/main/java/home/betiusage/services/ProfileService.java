package home.betiusage.services;

import home.betiusage.dto.ProfileDTO;
import home.betiusage.entities.Profile;
import home.betiusage.errorHandling.exception.NotFoundException;
import home.betiusage.repositories.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileDTO> findAll() {
        return profileRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ProfileDTO> findProfile(Long id) {
        return profileRepository.findById(id).map(this::toDTO);
    }

    public Profile findOrCreateProfile(String clerkId, String email, String username) {
        Optional<Profile> existingProfile = profileRepository.findByClerkId(clerkId);

        if (existingProfile.isPresent()) {
            Profile profile = existingProfile.get();

            boolean updateNeeded = false;

            if (email != null && !email.isEmpty() && !email.equals(profile.getEmail())) {
                profile.setEmail(email);
                updateNeeded = true;
            }

            if (username != null && !username.isEmpty() && !username.equals(profile.getUsername())) {
                profile.setUsername(username);
                updateNeeded = true;
            }

            if (updateNeeded) {
                return profileRepository.save(profile);
            }
            return profile;
        } else {
            Profile newProfile = new Profile();

            newProfile.setUsername(username != null && !username.isEmpty() ? username : "user");
            newProfile.setEmail(email != null && !email.isEmpty() ? email : "no-email@provided.com");
            newProfile.setClerkId(clerkId);

            return profileRepository.save(newProfile);
        }
    }

    public ProfileDTO deleteCurrentProfile(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile not found with id: " + id));

        logger.info("Request to delete profile with Clerk ID: {}", profile.getClerkId());
        ProfileDTO deletedProfileDTO = toDTO(profile);
        profileRepository.delete(profile);

        return deletedProfileDTO;
    }

    private ProfileDTO toDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setUsername(profile.getUsername());

        return profileDTO;
    }
}