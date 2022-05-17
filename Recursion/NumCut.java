package Recursion;

import java.io.*;
import java.util.*;


// 数的划分
public class NumCut {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        int res = process1(n, k);
//        HashSet<int[]> path = new HashSet<>();
//        int[] curPath = new int[k];
//        int res = process(0, k, n, 0, curPath, path);
        System.out.println(res);
    }

    // 方法1 寻找递推式

    // 小球放入箱子问题 将n个小球放入k个箱子 且每个箱子不为空
    // 可以将问题分解成两种情况
    // 第一种情况：箱子中存在只放一个小球的箱子  此时存放的方法个数等于 将n - 1个小球放入k - 1个箱子的方法个数
    // 第二种情况：箱子中不存在只放一个小球的箱子  此时存放的方法个数等于 将n - k个小球放入k个箱子的方法个数  (默认已经将每个箱子放入一个小球)
    public static int process1(int n, int k) {
        // 如果小球数量小于箱子数量 不存在可以放的方法
        if (n < k) {
            return 0;
        }
        // 若箱子数为1 或者 小球数等于箱子数 返回一种方法
        if (k == 1 || n == k) {
            return 1;
        }
        return process1(n - 1, k - 1) + process1(n - k, k);
    }

    // 方法2 暴力递归
    // 复杂度太高
    public static int process(int i, int k, int n, int curNum, int[] curPath, HashSet<int[]> path) {
        if (i == k) {
            Arrays.sort(curPath);
            if (!isValid(path, curPath) && curNum == n) {
                path.add(curPath);
                return 1;
            } else {
                return 0;
            }
        }
        int res = 0;
        for (int j = 1; j < n; j++) {
            if (curNum + j <= n) {
                int[] nextPath = new int[k];
                for (int index = 0; index < curPath.length; index++) nextPath[index] = curPath[index];
                nextPath[i] = j;
                res += process(i + 1, k, n, curNum + j, nextPath, path);
            }
        }
        return res;
    }

    public static boolean isValid(HashSet<int[]> path, int[] curPath) {
        for (int[] value : path) {
            if (Arrays.equals(curPath, value)) {
                return true;
            }
        }
        return false;
    }
}
