package va.renowave_service.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import va.renowave_service.common.LoginRequestDto;
import va.renowave_service.database.entity.MaintainerAccount;
import va.renowave_service.service.LoginService;
import va.renowave_service.service.LogoutService;
import va.renowave_service.service.SessionService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private LoginService loginService;
    private LogoutService logoutService;
    private SessionService sessionService;

    @PostMapping("/login")
    public ResponseEntity<Object> loginRequest(HttpSession httpSession, @Valid @RequestBody LoginRequestDto loginRequest) {
        loginService.loginUser(loginRequest);

        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logoutRequest(HttpSession httpSession) {
        logoutService.logout(httpSession);
        return new ResponseEntity<>("Logged out", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> isLoggedIn(HttpSession httpSession) {
        MaintainerAccount account = sessionService.findUserFromHttpSession(httpSession);
        if (account != null) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

}
