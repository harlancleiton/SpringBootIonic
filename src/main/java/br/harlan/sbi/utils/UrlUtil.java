package br.harlan.sbi.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class UrlUtil {
    public static List<Long> decodeLongId(String s) {
        String[] strings = s.split(",");
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++)
            list.add(Long.parseLong(strings[i]));
        return list;
        //return Arrays.asList(s.split(",")).stream().map(s1 -> Long.parseLong(sl)).collect(Collectors.toList());
    }

    public static String decodeString(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
