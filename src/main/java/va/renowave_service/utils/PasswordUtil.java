package va.renowave_service.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PasswordUtil {

    private final PasswordEncoder passwordEncoder;

    public String hashAndSaltPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean isPasswordValid(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}