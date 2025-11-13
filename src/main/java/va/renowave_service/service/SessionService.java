package va.renowave_service.service;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import va.renowave_service.database.entity.MaintainerAccount;
import va.renowave_service.database.repository.MaintainerAccountRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SessionService {

    private MaintainerAccountRepository accountRepository;

    public void grantSessionForUser(MaintainerAccount user) {
        Collection<GrantedAuthority> userAuthorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Authentication updatedAuthentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPasswordHash(), userAuthorities);
        SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
        log.warn("Granted session for user: {}", user.getUsername());
    }

    public MaintainerAccount findUserFromHttpSession(HttpSession httpSession) {
        SecurityContext context = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (context == null) {
            return null;
        }
        Optional<MaintainerAccount> accountOptional = accountRepository.findByUsername(String.valueOf(context.getAuthentication().getPrincipal()));
        return accountOptional.orElse(null);
    }

}
