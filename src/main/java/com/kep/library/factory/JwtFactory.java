package com.kep.library.factory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kep.library.dto.AccountDto;
import com.kep.library.dto.ManagerDto;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

//@Service

/**
 * JWT token을 발행하며, 토큰에 대한 유효성 검토를 진행한다.
 *
 * @version 1.0
 *
 */
public class JwtFactory {

    String signingKey = "sammanagerkey";

    public String generateToken(ManagerDto manager){
        String token = null;

        try{
            token = JWT.create()
                    .withIssuer("CNP")
                    .withClaim("ID", manager.getLibrarianId())
                    .withClaim("NAME", manager.getName())
                    .withClaim("ROLE", manager.getRole())
                    .withClaim("EXP", new Date(System.currentTimeMillis() + 60000))
                    .sign(generateAlgorithm());
        }catch (Exception e){

        }

        return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256(signingKey);
    }

    public AccountDto decodeJwt(String token){
        DecodedJWT decodedJWT = isValidToken(token)
                                .orElseThrow(() -> new NoSuchElementException("유효한 토큰이 아닙니다."));

        String id = decodedJWT.getClaim("ID").asString();
        String userName = decodedJWT.getClaim("USER").asString();
        String role = decodedJWT.getClaim("ROLE").asString();

        AccountDto accountDTO = new AccountDto(id, userName, role);
        return accountDTO;
    }

    private Optional<DecodedJWT> isValidToken(String token){
        DecodedJWT jwt = null;

        try{
            Algorithm algorithm = Algorithm.HMAC256(signingKey);
            JWTVerifier verifier = JWT.require(algorithm).build();

            jwt = verifier.verify(token);
        }catch(Exception e){

        }

        return Optional.of(jwt);
    }
}
