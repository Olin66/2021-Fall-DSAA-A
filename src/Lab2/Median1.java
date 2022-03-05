package Lab2;

import java.util.ArrayList;
import java.util.Scanner;


public class Median1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int test = sc.nextInt();
        long[] a1 = new long[n];
        long[] a2 = new long[n];
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < n; i++) a1[i] = sc.nextLong();
        for (int i = 0; i < n; i++) a2[i] = sc.nextLong();
        for (int i = 0; i < test; i++) {
            int l = sc.nextInt() - 1;
            int r = sc.nextInt() - 1;
            int k = r - l + 1;
            long result = findMedian(a1,a2,l,r,l,r,k);
            list.add(result);
        }
        for (Long l:list) System.out.println(l);
    }

    private static long findMedian(long[] a1, long[] a2, int l1, int r1, int l2, int r2, int k) {
        int len1 = r1-l1+1,len2=r2-l2+1;
        if (len1>len2) return findMedian(a2,a1,l2,r2,l1,r1,k);
        if (len1==0) return a2[l2+k-1];
        if (k==1) return (Math.min(a1[l1], a2[l2]));
        int i1 = l1+Math.min(len1,k/2)-1;
        int i2 = l2+Math.min(len2,k/2)-1;
        if (a1[i1]>a2[i2]) return findMedian(a1,a2,l1,r1,i2+1,r2,k-(i2-l2+1));
        else return findMedian(a1,a2,i1+1,r1,l2,r2,k-(i1-l1+1));
    }
}

/**************************************************************
 Problem: 1425
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1344 ms
 Memory:190188 kb
 ****************************************************************/
