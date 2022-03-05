package Lab3;

import java.util.Scanner;

public class InsertionOrSelection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for (int i = 0; i < test; i++) {
            int n = sc.nextInt();
            long[] a1 = new long[n];
            long[] a2 = new long[n];
            for (int k = 0; k < n; k++) {
                long l = sc.nextLong();
                a1[k] = l;
                a2[k] = l;
            }
            long c1 = insertionSort(a1);
            long c2 = selectionSort(a2);
//            System.out.println("c1=" + c1 + ",c2=" + c2);
            for (long l : a1) System.out.print(l + " ");
            if (c1 < c2) System.out.println("\nInsertion Sort wins!");
            else System.out.println("\nSelection Sort wins!");
        }
    }

    private static long selectionSort(long[] array) {
        long count = 0;
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (array[k] > array[j]) {
                    k = j;
                }
                count++;
            }
            count++;
            long temp = array[i];
            array[i] = array[k];
            array[k] = temp;
        }
        return count;
    }

    private static long insertionSort(long[] array) {
        long count = 0;
        int n = array.length;
        for (int i = 1; i < n; i++) {
            long temp = array[i];
            int j = i;
            while (j > 0 && temp < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
                count += 2;
            }
            if (j != i) {
                array[j] = temp;
                count++;
            }
        }
        return count;
    }
}

/**************************************************************
 Problem: 1430
 User: 12013006
 Language: Java
 Result: Accepted
 Time:500 ms
 Memory:40404 kb
 ****************************************************************/

