package home.betiusage.utils;

import home.betiusage.entities.Profile;
import home.betiusage.repositories.ProfileRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserUtil {

    private final ProfileRepository profileRepository;

    public CurrentUserUtil(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile getCurrentProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("No authentication found in context");
        }

        String profileId = authentication.getName();
        return profileRepository.findById(Long.valueOf(profileId))
                .orElseThrow(() -> new IllegalStateException("Profile not found for authenticated user"));
    }
}