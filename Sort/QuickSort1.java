/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

/**
 * 快速排序1.0
 * @author Xinnze
 */

import java.util.Arrays;


public class QuickSort1 {
    public static void quickSort1(int[] arr){
        if (arr == null || arr.length < 2){
         return ;
        }
        process(arr, 0, arr.length - 1);
    }
    
    //最后一个数p不管 把前面n-1个数分成 小于等于p 与 大于p 的区域
    public static void process(int [] arr, int L, int R){
        if (R == L || (R == L + 1 && arr[L] <= arr[R]))
            return ;
        if (R == L + 1){
            if (arr[R] < arr[L]){
                swap(arr, L, R);
                return ;
            }
        }
        int less = L - 1;
        int i = L;
        while (i < R){
            if (arr[i] <= arr[R]){
                swap(arr, i++, ++less);
            }
            else{
                i++;
            }
        }
        swap(arr, less + 1, R);
        if (less == L - 1)
            process(arr, L + 1, R);
        else if (less == R - 1)
            process(arr, L, R - 1);
        else{
            process(arr, L, less + 1);
            process(arr, less + 2, R);
        }     
    }
    
    public static void swap(int [] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public static void comparator(int [] arr){
        Arrays.sort(arr);
    }
    
    public static int [] generateRandomArray(int maxSize, int maxValue){
        // Math.random() -> [0, 1) double
        // Math.random() * A -> [0, A) double
        // int(Math.random() * A) -> [0, A-1] int1
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)((maxValue) * Math.random());
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
            quickSort1(arr1);
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
