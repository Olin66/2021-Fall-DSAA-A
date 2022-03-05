package Lab1;

import java.util.ArrayList;
import java.util.Scanner;

public class MagicNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Long> result = new ArrayList<>();
        result.add(0L);
        result.add(1L);
        result.add(8L);
        for (int i = 1; i < 1000000; i++) {
            if (judgeSymmetry(i)) {
                ArrayList<Long> temp = findMagic(String.valueOf(i));
                result.addAll(temp);
            }
        }
        Long[] array = result.toArray(new Long[0]);
        shellSort(array);
        ArrayList<String> strings = new ArrayList<>();
        while (sc.hasNext()) {
            long left = sc.nextLong();
            long right = sc.nextLong();
            long min = 0, max = 0;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > right) {
                    max = array[i - 1];
                    break;
                }
            }
            if (left == 1 || left == 0) {
                min = left;
            } else {
                for (int i = array.length - 1; i > 0; i--) {
                    if (array[i] < left) {
                        min = array[i + 1];
                        break;
                    }
                }
            }
            strings.add(min + " " + max);
        }
        for (String s : strings) System.out.println(s);
    }

    private static boolean judgeSymmetry(int num) {
        String ss = String.valueOf(num);
        for (int i = 0; i < ss.length(); i++) {
            if (ss.charAt(i) == '2' || ss.charAt(i) == '3' || ss.charAt(i) == '4' ||
                    ss.charAt(i) == '5' || ss.charAt(i) == '7') return false;
        }
        return true;
    }

    public static boolean find6or9(String s) {//true表示有6或9，false表示没有
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '6' || s.charAt(i) == '9') return true;
        }
        return false;
    }

    public static ArrayList<Long> findMagic(String s) {
        ArrayList<Long> list = new ArrayList<>();
        StringBuilder ss = new StringBuilder(s);
        char[] chars = new StringBuilder(s).reverse().toString().toCharArray();
        String sRe = new StringBuilder(s).reverse().toString();
        if (find6or9(s)) {
            int length = s.length();
            for (int i = 0; i < length; i++) {
                if (s.charAt(i) == '6') chars[length - i - 1] = '9';
                if (s.charAt(i) == '9') chars[length - i - 1] = '6';
            }
            sRe = new String(chars);
        }
        createArray(list, ss, sRe);
        return list;
    }

    private static void createArray(ArrayList<Long> list, StringBuilder ss, String sRe) {
        list.add(Long.parseLong(ss + sRe));
        list.add(Long.parseLong(String.valueOf(ss) + 0 + sRe));
        list.add(Long.parseLong(String.valueOf(ss) + 1 + sRe));
        list.add(Long.parseLong(String.valueOf(ss) + 8 + sRe));
    }

    public static void shellSort(Long[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                long temp = array[j];
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
 Problem: 1418
 User: 12013006
 Language: Java
 Result: Accepted
 Time:680 ms
 Memory:106268 kb
 ****************************************************************/
