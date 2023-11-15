package com.ot.onetool.Util;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.ot.onetool.POJO.UserPO;
//
//import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ot.onetool.POJO.UserPO;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class TokenUtil {
    private static final long EXPIRE_TIME= 10*60*60*1000;   //十小时
    private static final String TOKEN_SECRET="abcdefg";  //密钥盐
    public static String sign(UserPO userPO){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("dlr")
                    .withClaim("Username", userPO.getUid())
//                    .withAudience(userPO.getUsername())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("dlr").build();
            DecodedJWT jwt = verifier.verify(token);
            System.err.println("认证通过：");
            System.err.println("Username: " + jwt.getClaim("Username").asString());
            System.err.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
