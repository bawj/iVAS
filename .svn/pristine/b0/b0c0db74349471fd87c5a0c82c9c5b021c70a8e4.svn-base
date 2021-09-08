package com.yn.electricity.util;

import com.yn.electricity.utils.cron.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName: PasswordHelper
 * @Author: zzs
 * @Date: 2021/1/21 11:22
 * @Description: 密码加密
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private final static String algorithmName = "MD5";
    private final static int hashIterations = 2;

    /**
     * 密码加密
     * @param salt 用户注册是生成盐
     * @return
     */
    public static String encryptPassword(String password, String salt) {
        if(StringUtils.isEmpty(salt)){
            salt = generatorSalt();
        }
        String encryptPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(password + salt),
                hashIterations).toHex();
        return encryptPassword;
    }

    /**
     * 生成盐
     * @return
     */
    public static String generatorSalt(){
        return randomNumberGenerator.nextBytes().toHex();
    }


}
