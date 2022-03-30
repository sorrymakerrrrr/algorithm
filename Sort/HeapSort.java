/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

/**
 *
 * @author Xinnze
 */

import java.util.*;

public class HeapSort {
    
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2)
            return ;
// 形成大根堆 O(NlogN)
//        for (int i = 0; i < arr.length; i++){  // O(N)
//            heapInsert(arr, i);  // O(logN)
//        }
        // 形成大根堆 O(N)
        for(int i = arr.length - 1; i >= 0; i--){
            heapify(arr, i, arr.length);
        }
        
        int heapsize = arr.length;
        swap(arr, 0, --heapsize);  // 将arr[0]与arr[heapsize - 1]交换 即将最大值放在了最后一位
        while(heapsize > 0){  //O(N)    while(heapsize > 1)也可
            heapify(arr, 0, heapsize);  // 下次index为0的元素 O(logN)
            swap(arr, 0, --heapsize);  //O(1)
        }
        
    }
    
    // heapInsert 将插入一个元素的数组变成大根堆的形式
    public static void heapInsert(int[] arr, int index){
        //1.当前结点比父节点大 2.index != 0 则循环  (arr[0] > arr[(0 - 1) / 2]一定不成立)
        while(arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    
    // heapify 某个数在index位置，看能否往下沉
    // 不断与左右两个孩子比较
    // 较大的孩子如果大于当前的父，则交换
    public static void heapify(int arr[], int index, int heapsize){
        int left = 2 * index + 1;
        while(left < heapsize){  // 如果存在孩子结点
            int largest = (left + 1 < heapsize) && (arr[left] < arr[left + 1]) ?
            left + 1:
            left;  // 找出左右孩子中最大的结点的下标
            
            largest = arr[largest] > arr[index] ? largest : index;  // 找出孩子结点与父节点最大的结点的下标
            
            if(largest == index)  // 如果最大结点就是父节点 就不用下沉了
                break;
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
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
            heapSort(arr1);
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
