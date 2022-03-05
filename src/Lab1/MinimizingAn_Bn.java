package Lab1;

import java.util.Scanner;

public class MinimizingAn_Bn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();//用于存储扩充后的数组长度
        long h = sc.nextLong();//用于存储数组的边界
        long aMax = 0;
        long aMin = 1000000000;
        long aSum = 0;
        long bMax = 0;
        long bMin = 1000000000;
        long bSum = 0;
        for (int i = 0; i < n - 1; i++) {
            int input = sc.nextInt();
            aSum += input;
            if (input > aMax) aMax = input;
            if (input < aMin) aMin = input;
        }
        for (int i = 0; i < n - 1; i++) {
            int input = sc.nextInt();
            bSum += input;
            if (input > bMax) bMax = input;
            if (input < bMin) bMin = input;
        }
        if (n == 2 || h == 1) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        aSum = aSum - aMax - aMin;
        bSum = bSum - bMax - bMin;
        long An = aMax;
        long Bn = bMin;
        long hope = (aSum + An) - (bSum + Bn);
        if (hope == 1) System.out.println(An - Bn);
        else if (hope < 1) {
            System.out.println("IMPOSSIBLE");
        } else {
            if (An - hope + 1 <= aMin) {
                hope = hope - (An - aMin);
                An = 1;
                Bn = Bn + hope - 1;
                if (Bn >= bMax) Bn = h;
            } else An = An - hope + 1;
            System.out.println(An - Bn);
        }
    }
}

/**************************************************************
 Problem: 1417
 User: 12013006
 Language: Java
 Result: Accepted
 Time:772 ms
 Memory:187424 kb
 ****************************************************************/

