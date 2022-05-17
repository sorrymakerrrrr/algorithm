package String;

import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        KMP kmp = new KMP();
        System.out.println(kmp.isSub("aaxaabxahgchagyugayug", "ahg"));
//        System.out.println(Arrays.toString(kmp.getNextArray("aaxaab".toCharArray())));
    }

    public int isSub(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return -1;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[] next = getNextArray(chs2);
        int x = 0;
        int y = 0;
        while (x < chs1.length && y < chs2.length) {
            if (chs1[x] == chs2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return (y == chs2.length) ? x - y : -1;
    }

    public int[] getNextArray(char[] chs) {
        int len = chs.length;
        if (len == 1) return new int[]{-1};
        int[] next = new int[len];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;  //cn初始时代表 遍历到 i 时 i - 1的next  即cn = next[i - 1]
        int i = 2;
        while (i < len) {
            if (chs[i - 1] == chs[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}

