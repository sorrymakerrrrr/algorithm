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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
 
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        String[] nums = br.readLine().split(" ");
        int[] data = new int[size];
        for(int i = 0; i < size; i++){
            data[i] = Integer.parseInt(nums[i]);
            
        // bubble sort
        for (i = 0; i < size; i++){
            for (int j = 0; j < size - i - 1; j++){
                if (data[j] > data[j + 1]){
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
        
        // output
        StringBuilder sb = new StringBuilder();
        for (int d: data){
            sb.append(d + " ");
        }
        System.out.println(sb.toString());
        }
    }
}
