package sso.custom;

import org.jasig.cas.authentication.handler.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created by lzj on 2016/11/23.
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String password) {
        return null;
    }

    /**
     * 把字符串转换成md5
     *
     * @param str
     * @return
     */
    public static String strToMD5(String str)
            throws UnsupportedEncodingException {

        try {
            byte[] input;
            input = str.getBytes("UTF-8");
            return bytesToHex(bytesToMD5(input));
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }

    /**
     * 把字节数组转成16进位制数
     *
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 4; i < bytes.length - 4; i++) {
            digital = bytes[i];
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }

    /**
     * 把字节数组转换成md5
     *
     * @param input
     * @return
     */
    private static byte[] bytesToMD5(byte[] input) {
        byte[] buff = null;
        try {
            // 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算后获得字节数组
            buff = md.digest(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buff;
    }

}
