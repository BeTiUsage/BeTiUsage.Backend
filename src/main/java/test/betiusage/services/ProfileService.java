package test.betiusage.services;

import org.springframework.stereotype.Service;
import test.betiusage.dto.ProfileDto;
import test.betiusage.entitys.Profile;
import test.betiusage.repositorys.ProfileRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileDto> findAll() {
        return profileRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private ProfileDto toDTO(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(profile.getId());
        profileDto.setEmail(profile.getEmail());
        profileDto.setFirstName(profile.getFirstName());
        profileDto.setMiddleName(profile.getMiddleName());
        profileDto.setLastName(profile.getLastName());

        return profileDto;
    }
}