package com.yn.electricity.util;


import org.apache.commons.codec.binary.Base64;

public class PWDUtil {
    public final String DOMAIN_KEY = "gRdLRLLBteIXoy3tU+085w==";
    private final String CMS_KEY = "cssystem";

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public String encryptPassword(String password) {
        //password = password.replaceAll("\\s*", "");
        byte[] key = this.getDomainKey();
        DESUtil des = new DESUtil(key);
//        return new BASE64Encoder().encode(des.encrypt(password.getBytes()));
        return new String(Base64.encodeBase64(des.encrypt(password.getBytes())));
    }

    /**
     * 密码解密
     *
     * @param password
     * @return
     */
    public String decryptPassword(String password) {
        byte[] key = this.getDomainKey();
        DESUtil des = new DESUtil(key);
        String result = "";
        try {
            //result = new String(des.decrypt(new BASE64Decoder().decodeBuffer(password)));
            result = new String(des.decrypt(Base64.decodeBase64(password)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private byte[] getDomainKey() {
        DESUtil desUtil = new DESUtil(this.CMS_KEY.getBytes());
        byte[] result = new byte[0];
        try {
            //result = new BASE64Decoder().decodeBuffer(this.DOMAIN_KEY);
            result = Base64.decodeBase64(this.DOMAIN_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desUtil.decrypt(result);
    }

    public static void main(String[] args) {
        //密码加密
        PWDUtil pwdUtil = new PWDUtil();
        System.out.println(pwdUtil.encryptPassword("123456"));
    }
}
