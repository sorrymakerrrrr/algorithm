/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;
import java.util.*;


public class MergeSort2 {
    public static void mergeSort2(int [] arr) {
        if (arr == null || arr.length < 2)
            return ;
        
        int len = 1;
        while(len <= arr.length){
            // 循环终止条件 ： 剩下元素不超过一组时
            for(int i = 0; i + len < arr.length; i += 2 * len){
                int L = i;
                int R = i + 2 * len - 1;
                int M = L + (R - L) / 2;
                
                // 子表个数为奇数时 最后一个子表无需与其他子表进行排序
                //子表个数为偶数时，最后一个子表需要与前一个表进行排序
                if(R > arr.length - 1)
                    R = arr.length - 1;
                merge(arr, L, M, R);
            }
            len = 2 * len;
        }
    }
    
    public static void merge(int [] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while(p1 <= M && p2 <= R)
            help[i++] = (arr[p1] < arr[p2]) ? arr[p1++] : arr[p2++] ;
        while(p1 <= M)
            help[i++] = arr[p1++];
        while(p2 <= R)
            help[i++] = arr[p2++];
        for (i = 0; i < R - L + 1; i++)
            arr[L + i] = help[i];
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
            mergeSort2(arr1);
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
