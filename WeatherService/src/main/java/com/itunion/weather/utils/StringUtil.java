package com.itunion.weather.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 删除所有的HTML标签
     *
     * @param source 需要进行除HTML的文本
     * @return
     */
    public static String deleteAllHTMLTag(String source) {

        if (source == null) {
            return "";
        }

        String s = source;
        /** 删除普通标签  */
        s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
        /** 删除转义字符 */
        s = s.replaceAll("&.{2,6}?;", "");
        return s;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    public static String formatVar(String value){
        String _data = "{" + value.replace("var", "\"").replace(";", ",").replace("=", "\":") + "}";
        return replaceBlank(_data);
    }


    public static String stripHtml(String content) {
        content = content.replaceAll("\n", "");
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        // 去掉空格
        content = content.replaceAll(" ", "");
        content = content.replaceAll("&nbsp;", "");

        return content;
    }

    /**
     * 功能描述: 根据会员编号最后两位计算所存储空间下标
     * @return: 队列表名下标
     * @auther: 郑亮亮
     * @date: 2021年02月19日14:58:35
     */
    public static String getBlackListSplitIndex(String memberId) {
        return memberId.substring(memberId.length() - 2);
    }
    /**
     * 功能描述: 根据会员编号最后两位计算所存储队列log表所在DataSource下标
     * @return: 队列表名所在DataSource下标
     * @auther: 郑亮亮
     * @date: 2021年02月19日14:58:38
     */
    public static String getDataSourceIndex(String memberId) {
        Integer index = Integer.parseInt(memberId.substring(memberId.length() - 2))/10;
        String dataSourceIndex = index.toString();
        while (dataSourceIndex.length() < 2) {
            dataSourceIndex = dataSourceIndex+"0";
        }
        return dataSourceIndex;
    }
    public static boolean checkChannelCodes(String channelCodes,String channelCode) {
        List<String> list = Arrays.asList(channelCodes.split(","));
        return list.contains(channelCode);
    }

    public static void main(String args[]) {
        String memberId = "2000333600";
    }
}
