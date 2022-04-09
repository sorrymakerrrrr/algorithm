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
 

public class Solution {

    
    public static class Interval {
    int start;
    int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
        }
    }

public static ArrayList<Interval> insert (ArrayList<Interval> intervals, Interval newInterval) {
        // write code here
        if(intervals.isEmpty() || newInterval == null)
            return intervals;
        ArrayList<Interval> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        for(int d = 0; d < intervals.size(); d++){
            if(newInterval.start > intervals.get(i).end){
                i++;
                j++;
            }else if(newInterval.start <= intervals.get(i).end && newInterval.start >= intervals.get(i).start){
                if(newInterval.end < intervals.get(j).start){
                    newInterval.start = intervals.get(i).start;
                    break;
                }else if(newInterval.end >= intervals.get(j).start && newInterval.end <= intervals.get(j).end){
                    newInterval.start = intervals.get(i).start;
                    newInterval.end = intervals.get(j).end;
                    break;
                }else if(newInterval.end > intervals.get(j).end){
                    j++;
                }
            }else if(newInterval.start < intervals.get(i).start){
                if(newInterval.end < intervals.get(j).start){
                    j--;
                    break;
                }else if(newInterval.end >= intervals.get(j).start && newInterval.end <= intervals.get(j).end){
                    newInterval.end = intervals.get(j).end;
                    break;
                }else if(newInterval.end > intervals.get(j).end){
                    j++;
                }
            }
        }
        for(int d = 0; d < i; d++){
            res.add(intervals.get(d));
        }
        res.add(newInterval);
        for(int d = j + 1; d < intervals.size(); d++){
            res.add(intervals.get(d));
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<Interval> aa = new ArrayList<>();
        aa.add(new Interval(0, 4));
        aa.add(new Interval(5, 23));
//        aa.add(new Interval(10, 10));
//        aa.add(new Interval(12, 13));
//        aa.add(new Interval(16, 19));
        Interval b = new Interval(23, 30);
        ArrayList<Interval> res = insert(aa, b);
        for (Interval i : res){
            System.out.println(i.start);
            System.out.println(i.end);
        }
    }
}
//[[0,4],[5,23]],[23,30]