package pe.com.yape.config;

import java.text.ParseException;

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
