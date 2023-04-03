package org.example.common;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.exception.TokenException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utils {
    //随机数对象
    private static Random random = new Random();
    //token签名
    private static final String secret = "onlinelearn";
    //token 过期时间
    private static final int expire = 1800;
    ///生成一个验证码
    public static StringBuilder getCode(){
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10);
            code.append(r);
        }
        return code;
    }
    //公共方法(生成相关用户id等id)
    public static String getId(){
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(10);
            id.append(r);
        }
        return id.toString();
    }
    //公共方法(获取时间)
    public static String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }
    //生成一个token
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        //设置payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //设置过期时间
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire * 1000);
        //设置header
        Map<String,Object> header = new HashMap<>();
        header.put("alg","HS256");
        header.put("typ","JWT");
        return builder.withHeader(header).withExpiresAt(expireDate).sign(Algorithm.HMAC256(secret));
    }
    //验证token信息
    public static boolean checkToken(String token){
        boolean res = false;
        try{
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            res = true;
        }catch (SignatureVerificationException e){
            new TokenException("签名验证失败异常!");
            res = false;
        }catch (AlgorithmMismatchException e){
            new TokenException("加密算法匹配失败异常!");
            res = false;
        }
        catch (TokenExpiredException tokenExpiredException){
            new TokenException("token失效异常!");
            res = false;
        }catch (Exception e){
            new TokenException("其他异常!");
            res = false;
        }
        return res;
    }
}
