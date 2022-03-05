package Lab2;

import java.util.Scanner;

public class AdvancedMathematicsProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for (int i =0;i < test;i++){
            double target = sc.nextDouble();
            double x = findX(target,-1000000000,1000000000);
            System.out.println(x);
        }

    }

    private static double findX(double target, double min, double max) {
        double mid = min + (max - min) / 2;
        double result = mid * Math.exp(mid / 20);
        if (Math.abs(result - target) < 0.01) return mid;
        if (result > target) return findX(target, min, mid);
        else return findX(target, mid, max);
    }
}

/**************************************************************
 Problem: 1423
 User: 12013006
 Language: Java
 Result: Accepted
 Time:388 ms
 Memory:39560 kb
 ****************************************************************/

