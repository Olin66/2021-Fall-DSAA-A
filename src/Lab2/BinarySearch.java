package Lab2;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] array = new int[length];
        for (int i =0;i < array.length;i++) array[i] = sc.nextInt();
        shellSort(array);
        int test = sc.nextInt();
        for (int i = 0;i < test;i++){
            int num = sc.nextInt();
            if (binarySearch(array,num)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private static boolean binarySearch(int[] array, int num){
        int left = 0;
        int right = array.length-1;
        while (true){
            int mid = left + (right-left)/2;
            if (num==array[mid]) return true;
            else if (num<array[mid]) right=mid-1;
            else left=mid+1;
            if (left>right) return false;
        }
    }

    private static void shellSort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int temp = array[j];
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
 Problem: 1421
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1240 ms
 Memory:188224 kb
 ****************************************************************/