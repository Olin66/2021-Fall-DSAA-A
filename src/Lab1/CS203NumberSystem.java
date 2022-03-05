package Lab1;

import java.util.Scanner;

public class CS203NumberSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        String[] array = new String[test];
        for (int i = 0; i < test; i++) {
            int num = sc.nextInt();
            int[] arr = findDigit(num);
            int digit = arr[0];
            int digitNum = arr[1];
            array[i] = findNum(digit, digitNum);
        }
        for (String s : array) System.out.println(s);
    }

    private static int[] findDigit(int num) {//找到待查询的数字的位数和在其为该位数的第几个数
        int i = 1;//定位最大的位数
        double sum = 0;//用于存储不同位数的最大数字的多少
        int[] array = new int[2];
        if (num <= 3) {
            array[0] = 1;
            array[1] = num;
            return array;
        }
        while (true) {
            sum += Math.pow(3, i);
            if (num <= sum) {
                array[0] = i;
                break;
            }
            i++;
        }
        sum -= Math.pow(3, i);
        array[1] = (int) (num - sum);
        return array;
    }

    private static String findNum(int digit, int digitNum) {
        StringBuilder sb = new StringBuilder();
        for (int i = digit - 1; i >= 0; i--) {
            int test = (digitNum - 1) / (int) Math.pow(3, i);
            if (test == 0) sb.append("2");
            else if (test == 1) sb.append("3");
            else if (test == 2) sb.append("6");
            digitNum -= (int) Math.pow(3, i) * test;
        }
        return String.valueOf(sb);
    }
}

/**************************************************************
 Problem: 1416
 User: 12013006
 Language: Java
 Result: Accepted
 Time:236 ms
 Memory:19884 kb
 ****************************************************************/