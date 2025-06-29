package org.example.eatwarsaw.controller.login;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.example.eatwarsaw.dto.UserDto;
import org.example.eatwarsaw.dto.login.LoginRequest;
import org.example.eatwarsaw.dto.login.RegisterRequest;
import org.example.eatwarsaw.dto.login.LoginResponse;
import org.example.eatwarsaw.service.login.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private static final String ERROR = "error";

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        UserDto createdUser = loginService.registerUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/default_login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDto user = loginService.login(loginRequest);
        String token = loginService.loginAndGetToken(loginRequest);
        return ResponseEntity.ok(new LoginResponse(token, user));
    }

    @PostMapping("/google_login")
    public ResponseEntity<Map<String, String>> loginWithGoogle(@RequestBody Map<String, String> body) {
        String idToken = body.get("idToken");
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String email = decodedToken.getEmail();
            String name = decodedToken.getName();
            String jwtToken = loginService.loginOrRegisterFirebaseUser(email, name);

            Map<String, String> response = new HashMap<>();
            response.put("token", jwtToken);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(ERROR, "Invalid ID token"));
        }
    }


    @PostMapping("/facebook-login")
    public ResponseEntity<Map<String, String>> facebookLogin(@RequestBody Map<String, String> payload) {
        String idToken = payload.get("idToken");
        if (idToken == null) {
            return ResponseEntity.badRequest().body(Map.of(ERROR, "Missing idToken"));
        }
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String email = token.getEmail();
            String name = token.getName();
            String jwt = loginService.loginOrRegisterFirebaseUser(email, name);
            return ResponseEntity.ok(Map.of("token", jwt));
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(ERROR, "Invalid Firebase token: " + e.getMessage()));
        }
    }

}
