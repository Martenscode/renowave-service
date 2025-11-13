package va.renowave_service.service;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogoutService {

    public void logout(HttpSession httpSession) {
        SecurityContext context = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (context != null) {
            httpSession.invalidate();
            log.info("Invalidated session for user: {}", context.getAuthentication().getPrincipal());
        }
    }

}
