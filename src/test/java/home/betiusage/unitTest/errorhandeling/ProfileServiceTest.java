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
        ProfileDTO result = profileService.validateProfileDTO(dto);

        // Assert
        assertEquals(dto, result);
        verify(profileRepository, times(1)).findById(1L);
    }

    @Test
    void testValidateProfileDTO_invalidUsername_throwsException() {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(1L);
        dto.setEmail("test@example.com");
        dto.setUsername("");  // Invalid username

        Exception exception = assertThrows(ValidationException.class, () ->
                profileService.validateProfileDTO(dto));

        assertTrue(exception.getMessage().contains("username"));
    }

    @Test
    void testValidateProfileDTO_invalidEmail_throwsException() {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(1L);
        dto.setUsername("TestUser");
        dto.setEmail("");  // Invalid email

        Exception exception = assertThrows(ValidationException.class, () ->
                profileService.validateProfileDTO(dto));

        assertTrue(exception.getMessage().contains("email"));
    }

    @Test
    void testValidateProfileDTO_profileNotFound_throwsException() {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(1L);
        dto.setUsername("TestUser");
        dto.setEmail("test@example.com");

        when(profileRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () ->
                profileService.validateProfileDTO(dto));

        assertTrue(exception.getMessage().contains("does not exist"));
    }
}
