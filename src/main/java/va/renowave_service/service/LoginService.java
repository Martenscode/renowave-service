package va.renowave_service.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import va.renowave_service.common.LoginRequestDto;
import va.renowave_service.database.entity.MaintainerAccount;
import va.renowave_service.database.repository.MaintainerAccountRepository;
import va.renowave_service.utils.PasswordUtil;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class LoginService {

    private MaintainerAccountRepository accountRepository;
    private PasswordUtil passwordUtil;
    private SessionService sessionService;

    public void loginUser(LoginRequestDto loginRequest) {
        Optional<MaintainerAccount> accountOptional = accountRepository.findByUsername(loginRequest.getUsername());
        if (accountOptional.isEmpty() || !passwordMatches(accountOptional.get(), loginRequest)) {
            log.warn("Login failed - invalid credentials. USERNAME={}", loginRequest.getUsername());
            throw new IllegalArgumentException("Invalid credentials");
        }

        sessionService.grantSessionForUser(accountOptional.get());
    }

    private boolean passwordMatches(MaintainerAccount account, LoginRequestDto loginRequest) {
        String passwordHash = account.getPasswordHash();
        return passwordUtil.isPasswordValid(loginRequest.getPassword(), passwordHash);
    }

}
