/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkNode;

/**
 *
 * @author Xinnze
 */
public class Partition {
    
        public static class ListNode {
            int val;
            ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
            
    }
        
        public static ListNode init(int[] arr, int n){
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for(int i = 1; i < n; i++){
            ListNode next = new ListNode(arr[i]);
            cur.next = next;
            cur = next;
        }
        return head;
    }
        
        public static ListNode partition (ListNode head, int x) {
        // write code here
        // 四个结点 分别为小于头 小于尾 大于等于头 大于等于尾
        if (head == null){
            return head;
        }
        ListNode ls = null, le = null, ebs = null, ebe = null, next = null;
        ListNode cur = head;
        while(cur != null){
            // 将cur.next 断连
            next = cur.next;
            cur.next = null;
            if(cur.val < x){
                if(ls == null){
                    ls = cur;
                    le = cur;
                }else{
                    le.next = cur;
                    le = le.next;
                }
            }else if(cur.val >= x){
                if(ebs == null){
                    ebs = cur;
                    ebe = cur;
                }else{
                    ebe.next = cur;
                    ebe = ebe.next;
                }
            }
            cur = next;
        }
        if(ls == null){
            return ebs;
        }
        le.next = ebs;
        return ls;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 1, 4, 1};
        ListNode head = init(arr, arr.length);
        ListNode res = partition(head, 4);
        while(res != null){
            System.out.println(res.val);
            res = res.next;
        }      
    }
}
