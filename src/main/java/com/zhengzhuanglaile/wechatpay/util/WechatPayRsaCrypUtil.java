package com.zhengzhuanglaile.wechatpay.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Random;

public class WechatPayRsaCrypUtil {

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    /**
     * 签名
     * 
     * @param privateKey 私钥
     * @param plainText 明文
     * @return
     */
    public static String sign(PrivateKey privateKey, String plainText) {
        String sig = null;
        try {
            Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            sign.initSign(privateKey);
            sign.update(plainText.getBytes());
            byte[] signed = sign.sign();
            sig = org.apache.commons.codec.binary.Base64.encodeBase64String(signed);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return sig;
    }

    /**
     * 读取私钥
     * 
     * @param path
     * @return
     */
    public static String readPrivateKeyToString(String path) {
        String privateKey = null;
        try (FileInputStream fileInputStream = new FileInputStream(new File(path))) {
            ByteArrayOutputStream os = new ByteArrayOutputStream(2048);
            byte[] buffer = new byte[1024];
            for (int length; (length = fileInputStream.read(buffer)) != -1;) {
                os.write(buffer, 0, length);
            }
            privateKey = os.toString("UTF-8");
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

}
