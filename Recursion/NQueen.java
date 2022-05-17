package Recursion;

import java.util.*;
import java.lang.Math;

public class NQueen {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int res = nQueen2(n);
        System.out.println(res);
    }

    public static int nQueen1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }


    // 表示record[0...i-1]上都是已经摆放好的皇后  从第i个位置开始摆放
    // 皇后行的位置为 0 到 n-1
    // 当当前位置来到 n 时，说明存在一种合法的摆放方式
    public static int process1(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        // 每一层都有n个位置尝试
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        // 对0到i - 1的每一行皇后判断当前皇后摆放是否与之冲突
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }

    // 利用位运算计算 加快计算速度 但是只能计算32位以内的 即 0 <= n <= 32
    public static int nQueen2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 将所有的参数看成二进制状态码
    // limit 为总的皇后摆放位置 最右边的n位为1，剩余位为0
    // colLimit 为列限制 有限制的列为1，没有限制的列为0  （有限制即不可摆放的位置）
    // leftDiaLimit 为左下对角线限制 有限制的为1，没有限制的为0
    // rightDiaLimit 为右下对角线限制 有限制的为1，没有限制的为0
    public static int process2(int limit, int colLimit, int leftDiaLimit, int rightDiaLimit) {
        if (limit == colLimit) {
            return 1;
        }
        int res = 0;
        // pos 为现在的状态码 即可以选择的位置为1 其余不可选择的位置为0
        int pos = limit & (~(colLimit | leftDiaLimit | rightDiaLimit));
        while (pos != 0) {
            int moreRightOne = pos & (~pos + 1);
            pos = pos - moreRightOne;
            res += process2(limit,
                    (colLimit | moreRightOne),
                    (leftDiaLimit | moreRightOne) << 1,
                    (rightDiaLimit | moreRightOne) >>> 1);  // 无符号右移 >>>
        }
        return res;
    }
}
