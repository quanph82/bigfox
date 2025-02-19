/*
 * Author: QuanPH
 * Copyright @ 2015 by OneSoft.,JSC
 * 
 */
package vn.com.onesoft.bigfox.io.core.session;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author QuanPH
 */
public class BFUtils {

    private static SecureRandom random = new SecureRandom();

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String genRandomString(int length) {
        BigInteger bi = new BigInteger(130, random);
        return bi.toString(32);
    }
    
}
