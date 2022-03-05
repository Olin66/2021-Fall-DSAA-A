package Lab2;

import java.util.Scanner;

public class FindNumberPairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 1) {
            long num = sc.nextLong();
            for (int i = 0; i <= 31; i++) {
                long target = (long) Math.pow(2, i);
                if (target == num) {
                    System.out.println(1);
                    return;
                }
            }
            System.out.println(0);
            return;
        }
        long[] array = new long[n];
        for (int i = 0; i < n; i++) array[i] = sc.nextLong();
        int sum = 0;
        for (int i = 0; i <= 31; i++) {
            for (int j = 0; j < n - 1; j++) {
                long target = (long) (Math.pow(2, i) - array[j]);
                sum += binarySearch(array, j + 1, n - 1, target);
            }
        }
        System.out.println(sum);
    }

    private static int binarySearch(long[] arr, int left, int right, long target) {
        if (left > right) return 0;
        int sum = 0;
        int mid = left + (right - left) / 2;
        long midVal = arr[mid];
        if (target > midVal) return binarySearch(arr, mid + 1, right, target);
        else if (target < midVal) return binarySearch(arr, left, mid - 1, target);
        else {
            int index = mid - 1;
            while (index >= left && arr[index] == target) {
                sum++;
                index--;
            }
            sum++;
            index = mid + 1;
            while (index < arr.length && arr[index] == target) {
                sum++;
                index++;
            }
            return sum;
        }
    }

}

/**************************************************************
 Problem: 1424
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1132 ms
 Memory:65412 kb
 ****************************************************************/

