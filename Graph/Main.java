package Graph;

import java.util.*;
import java.lang.Math;

public class Main {
    // 最小移除的连续序列  得到最长严格单增序列
    public static int minRemove(int[] arr) {
        List<Integer> arrIndex = new ArrayList<>();
        arrIndex.add(0);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= arr[i - 1]) {
                arrIndex.add(i);
            }
        }
        int size = arrIndex.size();
        int res = Math.min(arr.length - arrIndex.get(1), arrIndex.get(size - 1));
        if (arr[arrIndex.get(size - 1)] > arr[arrIndex.get(1) - 1]) {
            res = Math.min(res, arrIndex.get(size - 1) - arrIndex.get(1));
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 4, 6, 9, 4, 6, 7, 9};
        int res = minRemove(arr);
        System.out.println(res);
    }
}
