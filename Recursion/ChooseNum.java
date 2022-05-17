package Recursion;

import java.io.*;
import java.util.*;
import java.lang.Math;

/*
 * 已知 n 个整数 x1,x2,…,xn，以及一个整数 k（k＜n）。从 n 个整数中任选 k 个整数相加，可分别得到一系列的和。
 * 例如当 n=4，k＝3，4 个整数分别为 3，7，12，19 时，可得全部的组合与它们的和为：
 *3＋7＋12=22　　3＋7＋19＝29　　7＋12＋19＝38　　3＋12＋19＝34。
 *现在，要求你计算出和为素数共有多少种。
 * */

public class ChooseNum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        String str1[] = br.readLine().split(" ");
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(str1[i]));
        }
        ArrayList<Integer> list = new ArrayList<>();
        getCombanie(arr, list, k, 0);
        System.out.println(list.size());
//        System.out.println((8 * 7 * 6 * 5 * 4 * 3 * 2)/(4 * 3 * 2));
        HashSet<Integer> set = new HashSet<>(getCombanie(arr, list, k, 0));//去除掉相同的结果
        System.out.println(set);
        System.out.println(set.size());
        int count = 0;
        for (Integer i : set) {
            count += isPrime(i);
        }
        System.out.println(count);

    }

    //arr存放的是n个数
    //list存放k个数相加的所有结果
    //k表示有k个数
    //result表示k个数相加的结果
    public static ArrayList getCombanie(ArrayList<Integer> arr, ArrayList<Integer> list, int k, int result) {
        if (k == 0) {
            list.add(result);
            return list;
        }

        for (int i = 0; i < arr.size(); i++) {
            int newresult = result + arr.get(i);
            ArrayList<Integer> newlist = new ArrayList<>(arr);//重新复制了一份数
            newlist.remove(i);//在新的list中删去已经选择的数字

            getCombanie(newlist, list, k - 1, newresult);//将新的列表作为参数传入到函数中，k的值减一，result的值也做想应改变
        }
        return list;
    }

    public static int isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return 0;
            }
        }
        return 1;
    }
}
