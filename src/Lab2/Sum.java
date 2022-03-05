package Lab2;

import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for (int i = 0;i <test;i++){
            int n = sc.nextInt();
            long ans = (long) n * (n+1)*(n+2)/6;
            System.out.println(ans);
        }
    }
}

/**************************************************************
 Problem: 1422
 User: 12013006
 Language: Java
 Result: Accepted
 Time:1068 ms
 Memory:110452 kb
 ****************************************************************/
