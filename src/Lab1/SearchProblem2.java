package Lab1;

import java.util.Scanner;

public class SearchProblem2 {
    //和problem1相同，但实际做法不应为此
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int[] array1 = new int[num1];
        for (int i = 0; i < num1; i++) array1[i] = sc.nextInt();
        int num2 = sc.nextInt();
        int[] array2 = new int[num2];
        for (int i = 0; i < num2; i++) array2[i] = sc.nextInt();
        judgeNum(array1, array2);
    }

    private static void judgeNum(int[] array1, int[] array2) {
        for (int j : array2) {
            if (checkNum(j, array1)) System.out.println("yes");
            else System.out.println("no");
        }
    }

    private static boolean checkNum(int num, int[] array) {
        for (int j : array) {
            if (j == num) return true;
        }
        return false;
    }
}
/**************************************************************
 Problem: 1415
 User: 12013006
 Language: Java
 Result: Accepted
 Time:2368 ms
 Memory:29708 kb
 ****************************************************************/