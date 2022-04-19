/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

import java.util.Comparator;
import java.io.*;
import java.util.Arrays;

/**
 * @author Xinnze
 */
public class ComparatorOverride {
    public static class Point {
        public int num1;
        public int num2;

        public Point(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Point[] arr = new Point[n];
        for (int i = 0; i < n; i++) {
            String s[] = br.readLine().split(" ");
            arr[i].num1 = Integer.parseInt(s[0]);
            arr[i].num2 = Integer.parseInt(s[1]);
        }

        Arrays.sort(arr, new PointCompare());
        System.out.println(arr[0].num1 + " " + arr[0].num2);
        int maxindex = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i].num1 > arr[maxindex].num1) {
                System.out.println(arr[i].num1 + " " + arr[i].num2);
                maxindex = i;
            }
        }
    }

    public static class PointCompare implements Comparator<Point> {
        @Override
        public int compare(Point a, Point b) {
            return b.num2 - a.num2;
        }
    }
}
