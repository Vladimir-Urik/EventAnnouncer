package lol.gggedr.eventannouncer.utils;

import java.util.List;

public class Strings {

    public static String join(List<String> strings, String join) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < strings.size(); i++) {
            builder.append(strings.get(i));
            if(i != strings.size() - 1) builder.append(join);
        }
        return builder.toString();
    }

    public static String randomAlphaNumeric(int count) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
