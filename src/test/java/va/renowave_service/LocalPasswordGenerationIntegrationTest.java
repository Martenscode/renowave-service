package va.renowave_service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import va.renowave_service.utils.PasswordUtil;

/**
 * Generate the password hash manually
 */
@SpringBootTest
class LocalPasswordGenerationIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(LocalPasswordGenerationIntegrationTest.class);

    @Autowired
    private PasswordUtil passwordUtil;

    @Test
    void generatePasswordHashLocally() {
        String password = "changeme";

        log.info("My generated password hash is: {}", passwordUtil.hashAndSaltPassword(password));
    }

}
