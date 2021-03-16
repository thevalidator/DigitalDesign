package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDecoder {

    private final static String pattern = "(?<number>\\d+)\\[(?<text>[a-zA-Z]+)\\]";
    private final static Pattern p = Pattern.compile(pattern);

    public static String getDecodedText(String text) {
        if (validateString(text)) {
            do {
                text = convertBlocks(text);
            } while (text.contains("["));
            return text;
        } else {
            throw new IllegalArgumentException("Incorrect text format");
        }
    }

    private static String convertBlocks(String text) {
        Matcher matcher = p.matcher(text);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb,
                    getText(matcher.group("text"), Integer.parseInt(matcher.group("number"))));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String getText(String s, int t) {
        String result = "";
        for (int i = t; i > 0; i--) {
            result = result.concat(s);
        }
        return result;
    }

    public static Boolean validateString(String text) {
        if (text.matches("^\\d+\\[([a-zA-Z0-9\\[\\]]+)\\]$")) {

            if(text.replaceAll("\\[", "").length() !=
                    text.replaceAll("\\]", "").length()) {
                return false;
            }

            String[] blocks = text.split("\\]");
            //Arrays.stream(blocks).forEach(s -> s = s.replaceAll("\\d+\\[", ""));
            for(int i = 0; i < blocks.length; i++) {
                blocks[i] = blocks[i].replaceAll("\\d+\\[", "");
                if(!blocks[i].matches("[a-zA-Z]+")) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }


}
