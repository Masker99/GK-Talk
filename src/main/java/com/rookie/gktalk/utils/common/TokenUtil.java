package com.rookie.gktalk.utils.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.utils.exception.WebException;
import com.rookie.gktalk.utils.validate.DataAssert;

/**
 * token工具类
 * @author Masker
 */
public class TokenUtil {
    /**
     * 加密密钥
     */
    private static String SECRET = "gktalk";

    public static void setSecret(String secret){
        SECRET = secret;
    }

    public static String getToken(User user,String role,String message){
        String token = "";
        setSecret(SECRET+user.getPassword());

        token = JWT.create()
                .withAudience(user.getName())
                .withClaim("role",role)
                .withClaim("message",message)
                .sign(Algorithm.HMAC256(SECRET));

        return token;
    }

    public static Result verifyToken(User user,String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET+user.getPassword())).build();

        try {
            jwtVerifier.verify(token);
        }catch (JWTVerificationException jwtVerificationException){
            jwtVerificationException.printStackTrace();
            throw new WebException("认证失败！");
        }

        return new Result(200,"认证成功",null);
    }

    public static String getAudience(String token){
        String username = JWT.decode(token).getAudience().get(0);
        DataAssert.notEmpty(username,"token丢失了");
        return username;
    }

    public static String getRole(String token){
        String role = JWT.decode(token).getClaim("role").toString();
        return role;
    }

    public static String getMessage(String token){
        String message = JWT.decode(token).getClaim("message").toString();

        return message;
    }
}
