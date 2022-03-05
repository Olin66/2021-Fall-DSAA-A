package Lab1;

import java.util.Scanner;

public class MathProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        String[] strings = new String[test];
        for (int i = 0; i < test; i++) {
            String ss = sc.next();
            String[] array = ss.split("\\+");
            if (judge(array)) strings[i] = "yes";
            else strings[i] = "no";
        }
        for (String s : strings) System.out.println(s);
    }

    private static boolean judge(String[] array) {
        for (String s : array) {
            if (s.endsWith("^x")) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '^') {
                        s = s.substring(0, i);
                        if (Integer.parseInt(s) > 1) return false;
                        break;
                    }
                }
            } else  {
                if (s.charAt(0) != '0') return false;
            }
        }
        return true;
    }
}

/**************************************************************
 Problem: 1419
 User: 12013006
 Language: Java
 Result: Accepted
 Time:496 ms
 Memory:41800 kb
 ****************************************************************/
