package Recursion;

import java.util.*;

public class FullyArranged {

    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        String s = cin.next();
        List<String> ans = new ArrayList<>();  // 答案的集合
        char[] chs = s.toCharArray();
        List<Character> set = new ArrayList<>();
        for (char value : chs) set.add(value);
        if (s.length() == 1) System.out.println(s);
        else {
            process(set, "", ans);
            for (String value : ans) {
                System.out.println(value);
            }
        }
        List<Integer> b = new ArrayList<>();
        b.add(1);
        b.add(2);
        b.add(3);
        b.remove(1);
        System.out.println(b);
        System.out.println(b.get(0));
        System.out.println(b.get(1));
    }
    // set中所有字符都可以选择
    // 形成的所有的全排列放入ans
    // 沿途的决定是path
    public static void process(List<Character> set, String path, List<String> ans) {
        if (set.isEmpty()) {
            ans.add(path);
        }
        for (int index = 0; index < set.size(); index++) {
            path = path + set.get(index);  // 注意点1：需要新建一个字符串 而不是在path上原地加
            List<Character> next = new ArrayList<>(set);  // 注意点2：存放未使用的集合也应该新建
            next.remove(index);
            process(next, path, ans);
        }
    }
}
