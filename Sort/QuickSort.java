/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

import java.util.Arrays;

/**
 * 真正的快排
 * @author Xinnze
 */
public class QuickSort {
    
    public static void quickSort(int [] arr){
        if (arr == null || arr.length < 2)
            return ;
        process(arr, 0, arr.length - 1);
    }
    
    public static void process(int [] arr, int L, int R){
        //只有在 L < R 时才执行操作
        if (L < R){
            // 将[L, R]范围的随机的一个数放到最后一位上
            swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);  // 做partition操作
            process(arr, L, p[0] - 1);
            process(arr, p[1] + 1, R);
        }
    }
    
    //这里的partition的target值就是arr[R]
    public static int [] partition(int[] arr, int L, int R){
        int i = L;
        int less = L - 1;
        int more = R;
        while(i < more){
            if (arr[i] < arr[R]){
                swap(arr, ++less, i++);
            }else if(arr[i] == arr[R]){
                i++;
            }else{
                swap(arr, i, --more);
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
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
            quickSort(arr1);
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
