package com.company;

import java.util.Scanner;

public class lab1_solutionOfB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int n = scanner.nextInt();

            String a1 = Integer.toBinaryString(a);
            String b1 = Integer.toBinaryString(b);

            int a2 = Integer.valueOf(a1, 2);
            int b2 = Integer.valueOf(b1, 2);

            int yushu = n % 3;
            if (yushu == 0) {
                System.out.println(a);
            } else if (yushu == 1) {
                System.out.println(b);
            } else if (yushu == 2) {
                int num = a2 ^ b2;
                System.out.println(num);
            }

        }
    }
}
