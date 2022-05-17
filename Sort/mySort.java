package Sort;

import java.lang.Math;
import java.util.Arrays;

public class mySort {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = (int) (Math.random() * 1000 - Math.random() * 1000);
        }
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(process1(0, 1, new String[]{"1", "2"}));
//        String s = "5_4_";
//        String[] ss = s.split("_");
//        System.out.println(Arrays.toString(ss));
//        System.out.println(ss.length);
    }
    public static boolean process1(int l, int r, String[] s) {
        int t = Integer.valueOf(s[l]);
        int left = l + 1;
        int right = r;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (Integer.valueOf(s[mid]) > t) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left == right;
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return ;
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l < r) {
            int x = l + (int) (Math.random() * (r - l + 1));
            swap(arr, x, l);
            int[] index = partition(arr, l, r);
            process(arr, l, index[0] - 1);
            process(arr, index[1] + 1, r);
        }

    }

    public static int[] partition(int[] arr, int left, int right) {
        int target = arr[right];
        int L = left - 1;  // 左边界
        int i = left;
        int R = right;
        while (i < R) {
            if (arr[i] < target) {
                swap(arr, i++, ++L);
            } else if (arr[i] == target) {
                i++;
            } else {
                swap(arr, i, --R);
            }
        }
        swap(arr, R, right);
        return new int[]{L + 1, R};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
