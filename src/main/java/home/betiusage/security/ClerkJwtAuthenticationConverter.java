package home.betiusage.security;

import home.betiusage.entities.Profile;
import home.betiusage.services.ProfileService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Map;

@Component
public class ClerkJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final ProfileService profileService;

    public ClerkJwtAuthenticationConverter(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        // Extract user info from JWT claims
        String clerkId = jwt.getSubject();

        // Check for a custom claims object that might contain user data
        Map<String, Object> userClaims = jwt.getClaims();

        // Extract email
        String email = extractClaimString(userClaims, "email");
        if (email == null) {
            email = extractClaimString(userClaims, "emailAddress");
        }
        if (email == null) {
            email = extractClaimString(userClaims, "primary_email_address");
        }

        // Extract name
        String username = extractClaimString(userClaims, "name");
        if (username == null) {
            username = extractClaimString(userClaims, "full_name");
        }
        if (username == null) {
            String firstName = extractClaimString(userClaims, "first_name");
            String lastName = extractClaimString(userClaims, "last_name");
            if (firstName != null) {
                username = lastName != null ? firstName + " " + lastName : firstName;
            }
        }
        if (username == null) {
            username = extractClaimString(userClaims, "username");
        }

        // If username is not available in JWT, use a part of the email or a default
        if (username == null || username.isEmpty()) {
            username = email != null ? email.split("@")[0] : "user";
        }

        // Find or create profile based on Clerk ID
        Profile profile = profileService.findOrCreateProfile(clerkId, email, username);

        // Create authentication token with basic role
        return new JwtAuthenticationToken(
                jwt,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                profile.getId().toString()
        );
    }

    //helper
    private String extractClaimString(Map<String, Object> claims, String key) {
        if (claims.containsKey(key)) {
            Object value = claims.get(key);
            if (value instanceof String) {
                return (String) value;
            }
        }
        return null;
    }
}