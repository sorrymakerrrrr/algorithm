package Recursion;

import java.util.*;


public class demo {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 用来计算一个数字有多少种不同的转换方法
     * @param number string字符串 给定的数字
     * @return int整型
     */
    public static List<String> translateNumber (String number) {
        // write code here
        List<String> ans = new ArrayList<>();
        HashMap<Integer, Character> numberToCh = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            numberToCh.put(i + 1, (char) (97 + i));
        }
        char[] chs = number.toCharArray();
        process(chs, "", ans, numberToCh);
        return ans;
    }
    public static void process(char[] chs, String path, List<String> ans, HashMap<Integer, Character> numberToCh) {
        if (chs.length == 0) {
            ans.add(path);
            return ;
        }
        if (chs.length > 1) {
            int num1 = ((int) (chs[0]) - 48) * 10 + ((int) (chs[1]) - 48);
            int num2 = (int) (chs[0]) - 48;
            if (num1 <= 26 && ((int) (chs[1]) - 48) == 0) {
                String nextPath1 = path + numberToCh.get(num1);
                char[] next1 = new char[chs.length - 2];
                for (int i = 2; i < chs.length; i++) next1[i - 2] = chs[i];
                process(next1, nextPath1, ans, numberToCh);
            }else if (num1 <= 26) {
                String nextPath1 = path + numberToCh.get(num1);
                char[] next1 = new char[chs.length - 2];
                for (int i = 2; i < chs.length; i++) next1[i - 2] = chs[i];
                process(next1, nextPath1, ans, numberToCh);
                String nextPath2 = path + numberToCh.get(num2);
                char[] next2 = new char[chs.length - 1];
                for (int i = 1; i < chs.length; i++) next2[i - 1] = chs[i];
                process(next2, nextPath2, ans, numberToCh);
            }else {
                String nextPath2 = path + numberToCh.get(num2);
                char[] next2 = new char[chs.length - 1];
                for (int i = 1; i < chs.length; i++) next2[i - 1] = chs[i];
                process(next2, nextPath2, ans, numberToCh);
            }

        }else{
            int num1 = (int) (chs[0]) - 48;
            String nextPath1 = path + numberToCh.get(num1);
            char[] next1 = new char[chs.length - 1];
            for (int i = 1; i < chs.length; i++) next1[i - 1] = chs[i];
            process(next1, nextPath1, ans, numberToCh);
        }
    }

    public static void main(String[] args) {
        List<String> ans = translateNumber("10198");
        System.out.println(ans);
    }
}