package com.springstudy.sociallogin.apple;

import com.springstudy.common.TokenDecoder;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.Security;

import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class GetMemberInfoService {

    private final AppleAuthClient appleAuthClient;

    private final AppleProperties appleProperties;

    public AppleIdTokenPayload get(String authorizationCode) {

        String idToken = appleAuthClient.getIdToken(
            appleProperties.getClientId(),
            generateClientSecret(),
            appleProperties.getGrantType(),
            authorizationCode
        ).getIdToken();

        return TokenDecoder.decodePayload(idToken, AppleIdTokenPayload.class);
    }

    private String generateClientSecret() {

        LocalDateTime expiration = LocalDateTime.now().plusMinutes(5);

        return Jwts.builder()
            .setHeaderParam(JwsHeader.KEY_ID, appleProperties.getKeyId())
            .setIssuer(appleProperties.getTeamId())
            .setAudience(appleProperties.getAudience())
            .setSubject(appleProperties.getClientId())
            .setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
            .setIssuedAt(new Date())
            .signWith(getPrivateKey(), SignatureAlgorithm.ES256)
            .compact();
    }

    private PrivateKey getPrivateKey() {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");

        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(appleProperties.getPrivateKey());

            PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(privateKeyBytes);
            return converter.getPrivateKey(privateKeyInfo);
        } catch (Exception e) {
            throw new RuntimeException("Error converting private key from String", e);
        }
    }
}
