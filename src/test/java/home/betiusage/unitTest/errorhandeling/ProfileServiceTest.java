package home.betiusage.unitTest.errorhandeling;

import home.betiusage.dto.ProfileDTO;
import home.betiusage.entities.Profile;
import home.betiusage.errorhandeling.exception.NotFoundException;
import home.betiusage.errorhandeling.exception.ValidationException;
import home.betiusage.repositories.ProfileRepository;
import home.betiusage.services.HobbyService;
import home.betiusage.services.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    private ProfileRepository profileRepository;
    private HobbyService hobbyService;
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        profileRepository = mock(ProfileRepository.class);
        hobbyService = mock(HobbyService.class);
        profileService = new ProfileService(profileRepository, hobbyService);
    }

    @Test
    void testValidateProfileDTO_validInput_returnsSameDTO() {
        // Arrange
        ProfileDTO dto = new ProfileDTO();
        dto.setId(1L);
        dto.setEmail("test@example.com");
        dto.setUsername("TestUser");

        when(profileRepository.findById(1L)).thenReturn(Optional.of(new Profile()));

        // Act
        ProfileDTO result = profileService.validateProfileDTOUsername(dto);

        // Assert
        assertEquals(dto, result);
        verify(profileRepository, times(1)).findById(1L);
    }

    @Test
    void testValidateProfileDTO_invalidUsername_throwsException() {
        // Arrange
        ProfileDTO dto = new ProfileDTO();
        dto.setId(1L);
        dto.setEmail("test@example.com");
        dto.setUsername("");  // Invalid username

        // Act
        Exception exception = assertThrows(ValidationException.class, () ->
                profileService.validateProfileDTOUsername(dto));

        // Assert
        assertTrue(exception.getMessage().contains("username"));
    }
}
