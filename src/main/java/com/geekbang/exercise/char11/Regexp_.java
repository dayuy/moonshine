package com.geekbang.exercise.char11;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp_ {
    @Test
    public void patternTest() {
        String content = "这个是hello一个参数yong测试。";
        // 1、创建一个Pattern对象，一个正则表达式对象
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        // 2、创建一个匹配器对象
        Matcher matcher = pattern.matcher(content);
        // 3、开始循环匹配
        while (matcher.find()) {
            // 匹配内容，在m.group(0)中。
            System.out.println("找到： " + matcher.group(0));
        }
    }

    @Test
    public void digitTest() {
        String content = "这是1990年的某一天";
        Pattern pattern = Pattern.compile("(\\d\\d)(\\d\\d)");
        Pattern pattern1 = Pattern.compile("大白兔(?:奶糖|雪糕|纪念册)");
        Pattern pattern2 = Pattern.compile("大白兔(?=奶糖|雪糕|纪念册)");
        Pattern pattern3 = Pattern.compile("大白兔(?!奶糖|雪糕|纪念册)");
        Pattern pattern4 = Pattern.compile("\\d+"); // 默认贪婪模式 1990
        Pattern pattern5 = Pattern.compile("\\d+?"); // 非贪婪模式 1

        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("匹配到：" + matcher.group(0)); // 1990
            System.out.println("其中匹配到的第一组：" + matcher.group(1)); // 19
            System.out.println("其中匹配到的第2组：" + matcher.group(2)); // 90
        }

        /**
         * matcher.group(0) 源码分析：
         * public String group(int group) {
         *     return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
         * }
         * 得到：groups[0] = 2 和 groups[1]=5 为匹配的所有
         *      groups[2] = 2 和 groups[3]=3 为匹配的第一组，第一个括号
         *      groups[4] = 4 和 groups[5]=5 为匹配的第二组，第二个括号
         *
         * 再次执行find时，就从content的5开始匹配
         * **/
    }

    @Test
    public void homework() {
        // 中文
        String content = "大白兔奶糖";
        String regStr = "^[\u0391-\uffe5]+$";
        // 邮政编码：1-9开头的一个六位数
        String regStr2 = "^[1-9]\\d{5}$";
        // 链接 https://www.bilibili.com/video/BV1fh411y7R8?from=search&seid=18310
        String content3 = "https://www.bilibili.com/video/BV1fh411y7R8?from=search&seid=18310";
        String regStr3 = "^((http|https)://)([\\w-]+\\.)+[\\w-]+(/[\\w-?=&/%.]*)?$"; // []内的.就是.本身，不需要转义字符

        Pattern pattern = Pattern.compile(regStr3);
        Matcher matcher = pattern.matcher(content3);
        if (matcher.find()) {
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }

        // matches
        System.out.println(Pattern.matches(regStr3, content3));
        // Matcher 类相关方法 matches()、find()、find(int start)、lookingAt()、end()、end(int group)、start()、start(int group)
        String newContent = matcher.replaceAll("替换为的内容");
    }

    @Test
    public void test12() {
        // 反向引用
        String content = "hello jack tom11 jack22 yyy xxx";
        String regStr = "(\\d)\\1";

        String content1 = "hello jack tom11 jack2442 yyy xxx";
        String regStr1 = "(\\d)(\\d)\\2\\1"; // 利用反向引用 匹配2332、5665回文数字

        /**
         * 请检索满足这个的商品编号，形式如 12321-333999111
         * 要求满足前面是一个五位数，一个-，然后一个九位数，连续的每个三位数要相同
         * **/
        String content2 = "12321-333999111";
        String regStr2 = "\\d{5}-(\\d)\\1{2}(\\d)\\2{2}(\\d)\\3{2}";
        Pattern pattern = Pattern.compile(regStr1);
        Matcher matcher = pattern.matcher(content1);
        while (matcher.find()) {
            System.out.println("找到： " + matcher.group(0));
        }
    }

    @Test
    public void test13() {
        // 去掉重复字：我我要大大大白兔
        String content = "我我要大大大白兔";
        Pattern pattern = Pattern.compile("(.)\\1+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        content = matcher.replaceAll("$1");
        System.out.println("new content=" + content);
    }

    @Test
    public void testString() {
        String content = "我我要#大@大~大白兔";
        content = content.replaceAll("兔", "mao");
        if (content.matches("\\d+")) {
            System.out.println("匹配成功");
        }
        String[] strs = content.split("#|-|~\\d+");
        for (String s : strs) {
            System.out.println(s);
        }
    }

}

/**
 * String regStr = "abc" 匹配abc字符串【默认区分大小写】
 * String regStr = "(?i)abc"  匹配abc「不区分大小写」 "a(?i)bc" bc不区分大小写；"a((?i)b)c" 只有b不区分大小写
 * Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
 *
 * \\s 匹配任何空白字符（空格，制表符
 * ab|cd  匹配ab或cd
 * ""a{3,4}" 尽量匹配多的，贪婪模式
 * +  1到多
 * *  0到多
 * ？ 0到1
 * 定位符：^ 起始
 *       $ 结束
 *       \\b 匹配目标字符串的边界 han\\b  "hanshunpingsphan nnhan"
 *       \\B  匹配目标字符串的非边界 han\B
 * 分组
 * (\\d\\d)(\\d\\d) 分组
 * (?<g1>\\d\\d)(?<g2>\\d\\d) 命名分组 matcher.group("g1") 或 matcher.group(1)
 *
 * 非捕获分组
 * (?:pattern) 不存储来供以后使用的匹配。"industr(?:y|ies)" 比 "industry|industries"更经济的表达式
 * (?=pattern) 非捕获匹配，"Windows (?=95|98|NT|2000)" 匹配"Windows 2000"中的Windows，不不匹配"Windows 3.1"中的Windows
 * (?!pattern) 非捕获匹配，匹配与(?=pattern)相反。"Windows (?!95|98|NT|2000)" 匹配"Windows 3.1"中的Windows，但不匹配"Windows 2000"中的Windows
 *
 * 注意
 * [. * ?]内的.就是.本身，不需要转义字符
 *
 * 反向引用
 * ：圆括号的内容被捕获后，可以在这个括号后被使用，从而写出一个比较实用的匹配模式。
 *   在正则表达式内部使用 \\分组号；在外部使用 $分组号
 * **/



































