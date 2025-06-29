package org.example.eatwarsaw.service.login;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.example.eatwarsaw.dto.login.DecodedTokenInfo;
import org.example.eatwarsaw.exception.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    public DecodedTokenInfo verifyToken(String idToken) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return new DecodedTokenInfo(token.getEmail(), token.getName());
        } catch (FirebaseAuthException e) {
            throw new UnauthorizedException("Invalid ID token");
        }
    }
}
