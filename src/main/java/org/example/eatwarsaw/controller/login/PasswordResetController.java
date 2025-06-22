package org.example.eatwarsaw.controller.login;

import org.example.eatwarsaw.service.EmailService;
import org.example.eatwarsaw.service.UserService;
import org.example.eatwarsaw.service.login.LoginService;
import org.example.eatwarsaw.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class PasswordResetController {

    private final LoginService loginService;

    public PasswordResetController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        boolean sent = loginService.sendPasswordResetEmail(email);
        if (!sent) {
            return ResponseEntity.badRequest().body("User with email not found");
        }
        return ResponseEntity.ok("Password reset email sent");
    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");
        boolean success = loginService.resetPassword(token, newPassword);
        if (!success) {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }
        return ResponseEntity.ok("Password updated successfully");
    }
}
