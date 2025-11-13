package va.renowave_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        int saltLength = 16; // 16 bytes
        int hashLength = 32; // 32 bytes (256 bits)
        int parallelism = 1;
        int memory = 10 * 1024; // 10 MiB
        int iterations = 2;

        return new Argon2PasswordEncoder(saltLength, hashLength, parallelism, memory, iterations);
    }

}