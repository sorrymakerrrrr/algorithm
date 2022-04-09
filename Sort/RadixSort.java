/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;
import java.util.*;
import java.lang.Math;

/**
 *
 * @author Xinnze
 */
public class RadixSort {
    public static void radixSort(int [] arr){
        if(arr == null || arr.length < 2)
            return ;
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }
    
    public static int maxbits(int [] arr){
        int maxindex = 0;
        for(int i = 1; i < arr.length; i++){
            if (arr[i] > arr[maxindex])
                maxindex = i;
        }
        int res = 0;
        int maxnum = arr[maxindex];
        while(maxnum != 0){
        maxnum /= 10;
        res++;
        }
        return res;
    }
    
    // 获取整型x的第d位的数  第一位是个位，第二位是十位...
    public static int gitdigit(int x, int d){
        return (x / (int)(Math.pow(10, d - 1))) % 10;
    }
    
    
    //digit是最大数的位数
    public static void radixSort(int [] arr, int L, int R, int digit){
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少数就准备多少个辅助空间
        int[] bucket = new int[R - L + 1];
        // 做最大位数次数的循环 将所有数按照个位大小、十位大小...排序
        for(int d = 1;d <= digit; d++){
            
            int[] count = new int[radix];  // count = [0,0,...,0]  10个0
            
            // count计数 count[j] 为当前位值为j的数的总数
            for (i = L; i <= R; i++){
                j = gitdigit(arr[i], d);  // 获取每个数当前位的值
                count[j]++;
            }
            
            // 累加 count[j]为当前位值小于等于j的数的总和
            for(i = 1; i < radix; i++){
                count[i] = count[i] + count[i - 1];
            }
            
            // 将所有数填入bucket中 此时按照当前位从小到大排序
            for(i = R; i >= L; i--){
                j = gitdigit(arr[i], d);
                bucket[count[j]-1] = arr[i];
                count[j]--;
            }
            
            // 将bucket copy到arr
            for(i = 0; i < bucket.length; i++){
                arr[L + i] = bucket[i];
            }           
        }
    }
    public static void comparator(int [] arr){
        Arrays.sort(arr);
    }
    
    public static int [] generateRandomArray(int maxSize, int maxValue){
        // Math.random() -> [0, 1) double
        // Math.random() * A -> [0, A) double
        // int(Math.random() * A) -> [0, A-1] int
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)((maxValue + 1) * Math.random());
        }
        return arr;
    }
    
    public static int [] copyArray(int [] arr){
        if (arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            res[i] = arr[i];
        }
        return res;
    }
    
    public static boolean isEqual(int[] arr1, int[] arr2){
        if (arr1 == null && arr2 != null || arr1 != null && arr2 == null)
            return false;
        if(arr1 == null && arr2 == null)
            return true;
        if(arr1.length != arr2.length)
            return false;
        for (int i = 0; i < arr1.length; i++){
            if(arr1[i] != arr2[i])
                return false;        
        }
        return true;
    }
    
    public static void printArray(int[] arr){
        if (arr == null)
            return ;
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i] + " ");
        }
        System.out.println();
    }
    
    //for test 对数器
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++){
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)){
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                System.out.println(i);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Shit!");
    }

}
