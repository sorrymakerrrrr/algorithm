/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

import java.util.Arrays;

/**
 * 递归版本
 * @author Xinnze
 */
public class MergeSort {
    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        // 归并排序 递归调用
        process(arr, 0, arr.length - 1);
    }
    
    public static void process(int[] arr, int left, int right){
//        if (left == right)
//            return;
        if(left < right){
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, right, mid);
        }
    }
    
    public static void merge(int[] arr, int l, int r, int m){
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        
        while(p1 <= m && p2 <= r){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++]: arr[p2++];
        }
        while(p1 <= m){
            help[i++] = arr[p1++];
        }
        while(p2 <= r){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++){
            arr[l + j] = help[j];  // 注意是arr[l + j] 而不是arr[j]
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
            mergeSort(arr1);
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
