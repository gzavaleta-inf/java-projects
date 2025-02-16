package pe.com.bcp.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OAuthToken {
    public boolean checkValidToken(String authorizationToken) {

        SignedJWT signedJWT = null;

        try {
            signedJWT = SignedJWT.parse(authorizationToken);
        }
        catch (ParseException ex) {
            log.error("Error reading scopes", ex);
            return false;
        }

        return (signedJWT != null) ? true : false;
    }
}
