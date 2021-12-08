package com.itunion.weather.utils;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeUtil {
    //编码
    public static String encode(String string) {
        StringBuilder sbr = new StringBuilder(string.length() * 4);
        for (char ch : string.toCharArray()) {
            if (ch > 0xfff) {
                sbr.append("\\u");
                sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
            } else if (ch > 0xff) {
                sbr.append("\\u0");
                sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
            } else if (ch > 0x7f) {
                sbr.append("\\u00");
                sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
            } else if (ch < 32) {
                switch (ch) {
                    case '\b':
                        sbr.append('\\');
                        sbr.append('b');
                        break;
                    case '\n':
                        sbr.append('\\');
                        sbr.append('n');
                        break;
                    case '\t':
                        sbr.append('\\');
                        sbr.append('t');
                        break;
                    case '\f':
                        sbr.append('\\');
                        sbr.append('f');
                        break;
                    case '\r':
                        sbr.append('\\');
                        sbr.append('r');
                        break;
                    default:
                        if (ch > 0xf) {
                            sbr.append("\\u00");
                            sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
                        } else {
                            sbr.append("\\u000");
                            sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
                        }
                        break;
                }
            } else {
                switch (ch) {
                    case '\'':
                        sbr.append("\\u0027");
                        break;
                    case '"':
                    case '\\':
                        sbr.append("\\");
                        sbr.append(ch);
                        break;
                    default:
                        sbr.append(ch);
                        break;
                }
            }
        }
        return sbr.toString();
    }

    //解码
    public static String decode(String unicode) {
        char[] chars = unicode.toCharArray();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0, len = chars.length; i < len;) {
            char c = chars[i++];
            switch (c) {
                case 0:
                case '\n':
                case '\r':
                    break;
                case '\\':
                    c = chars[i++];
                    switch (c) {
                        case 'b':
                            buffer.append('\b');
                            break;
                        case 't':
                            buffer.append('\t');
                            break;
                        case 'n':
                            buffer.append('\n');
                            break;
                        case 'f':
                            buffer.append('\f');
                            break;
                        case 'r':
                            buffer.append('\r');
                            break;
                        case 'u':
                            buffer.append((char) Integer.parseInt(new String(new char[] {//
                                    chars[i++], chars[i++], chars[i++], chars[i++] //
                            }), 16));//
                            break;
                        case 'x':
                            buffer.append((char) Integer.parseInt(new String(new char[] {//
                                    chars[i++], chars[i++] //
                            }), 16));//
                            break;
                        default:
                            buffer.append(c);
                    }
                    break;
                default:
                    buffer.append(c);
            }
        }
        return buffer.toString();
    }

    /**
     * 方法2
     * @param unicodeStr
     * @return
     */
    public static String unicodeToString(String unicodeStr) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(unicodeStr);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            unicodeStr = unicodeStr.replace(matcher.group(1), ch + "");
        }
        return unicodeStr;
    }

    public static void main(String[] args) {
        String unicode = encode("你好Unicode");
        System.out.println(unicode);

        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sbr.append(unicode);
        }
        unicode = sbr.toString();

        //System.out.println(decode(unicode));
        long l = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            decode(unicode);
            //unicodeToString(unicode);
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}