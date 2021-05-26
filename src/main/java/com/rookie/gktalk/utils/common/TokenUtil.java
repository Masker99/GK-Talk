package com.rookie.gktalk.utils.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.utils.exception.WebException;
import com.rookie.gktalk.utils.validate.DataAssert;

public class TokenUtil {
    private static String SECRET = "com.rookie.gktalk.";

    public static void setSecret(String secret){
        SECRET = secret;
    }

    public static String getToken(User user){
        String token = "";
        setSecret(SECRET+user.getPassword());

        token = JWT.create()
                .withAudience(user.getName())
                .sign(Algorithm.HMAC256(SECRET));

        return token;
    }

    public static Result verifyToken(User user,String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET+user.getPassword())).build();
        System.out.print(jwtVerifier.toString());
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
        }catch (JWTVerificationException jwtVerificationException){
            throw new WebException("认证失败！");
        }

        return new Result(200,"认证成功",null);
    }

    public static String getAudience(String token){
        String username = JWT.decode(token).getAudience().get(0);
        DataAssert.notEmpty(username,"token丢失了");
        return username;
    }
}
