package net.panamiur.vieneviene.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by gnu on 18/11/16.
 */

public class Base64Code {

    public static String encode(String text) throws UnsupportedEncodingException {
        byte[] data = text.getBytes("UTF-8");
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    public static String decode(String base64) throws UnsupportedEncodingException {
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        return new String(data, "UTF-8");
    }
}
