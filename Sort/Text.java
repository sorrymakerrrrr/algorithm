/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

import java.util.Arrays;
import java.lang.Math;

/**
 *
 * @author Xinnze
 */
public class Text {
    
    //radixSort
    public static void radixSort(int [] arr){
        if(arr == null || arr.length < 2)
            return ;
        int bit = maxbits(arr);
        radixSort(arr, 0, arr.length - 1, bit);
    }
    
    public static void radixSort(int []arr, int L, int R, int bit){
        int[] bucket = new int[R - L + 1];
        for(int d = 1; d <= bit; d++){
            int[] radix = new int[10];
            for(int i = L; i <= R; i++){
                int num = ((arr[i] / (int)Math.pow(10, d - 1)) % 10);
                radix[num]++;
            }
            for(int i = 1; i < radix.length; i++){
                radix[i] = radix[i - 1] + radix[i];
            }
            
            for(int i = R; i >= L; i--){
                int num = ((arr[i] / (int)Math.pow(10, d - 1)) % 10);
                bucket[--radix[num]] = arr[i];
            }
            for(int i = 0; i < R - L + 1; i++){
                arr[L + i] = bucket[i];
            }
        }
    }
    
    public static int maxbits(int [] arr){
        int maxindex = 0;
        for(int i = 0; i < arr.length; i++){
            maxindex = (arr[i] > arr[maxindex]) ? i : maxindex;
        }
        int bit = 0;
        int maximum = arr[maxindex];
        while(maximum != 0){
            bit++;
            maximum /= 10;
        }
        return bit;
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
        // int(Math.random() * A) -> [0, A-1] int
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(maxValue * Math.random());
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
