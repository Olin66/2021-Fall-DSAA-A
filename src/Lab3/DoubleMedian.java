package Lab3;

import java.util.Scanner;

public class DoubleMedian {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) array[i] = sc.nextLong();
        shellSort(array);
        if (n % 2 == 0) System.out.println(array[n / 2] + array[n / 2 - 1]);
        else System.out.println(2*array[(n-1) / 2]);
    }

    private static void shellSort(long[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                long temp = array[j];
                while (j - gap >= 0 && temp < array[j - gap]) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = temp;
            }
        }
    }
}

/**************************************************************
 Problem: 1429
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1340 ms
 Memory:196796 kb
 ****************************************************************/
