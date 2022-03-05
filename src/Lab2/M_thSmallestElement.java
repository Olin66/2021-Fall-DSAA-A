package Lab2;

import java.util.Scanner;

public class M_thSmallestElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for (int k = 0; k < test; k++) {
            long N = sc.nextLong();
            long M = sc.nextLong();
            long max = 7500000000L;
            long min = -38081238L;
            System.out.println(findNum(M, min, max, N));
        }
    }

    private static long findNum(long M, long min, long max, long N) {
        while (min < max) {
            long mid = min + ((max - min) >> 1L);
            long count = count(N, mid);
            if (count < M) min = mid + 1L;
            else max = mid;
        }
        return min;
    }

    private static long count(long N, long mid) {//统计整个矩阵中小于mid值的个数
        long count = 0L;
        for (long j = 1L; j <= N; j++) count += subFindNum(j, mid, N);
        return count;
    }

    private static long subFindNum(long col, long target, long N) {//每列查找小于target的数的数量
        long l = 1L;
        long r = N;
        while (l < r) {
            long mid = (l + r + 1) / 2;
            if (calculate(mid, col) <= target) l = mid;
            else r = mid - 1;
        }
        if (calculate(l, col) > target) return 0L;
        return l;
    }

    private static long calculate(long row, long col) {
        return row * row + col * col + 12345L * (row - col) + row * col;
    }
}

/**************************************************************
 Problem: 1426
 User: 12013006
 Language: Java
 Result: Accepted
 Time:580 ms
 Memory:23764 kb
 ****************************************************************/

