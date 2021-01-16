package com.company;

import java.util.Scanner;

public class lab2_solutionOfB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int t = scanner.nextInt();
        int[] numArray = new int[n];
        int[] errArray = new int[t];


        for (int i = 0; i < n; i++) {
            numArray[i] = scanner.nextInt();
        }
        for (int i = 0; i < t; i++) {
            errArray[i] = scanner.nextInt();
        }

        for (int i = 0; i < t; i++) {
            int place =0;
            for (int j = 0; j < n-1; j++) {
               if(errArray[i]>numArray[n-1]){
                   System.out.println(errArray[i]-numArray[n-1]);
                   break;
               }else if(errArray[i]==numArray[n-1]){
                   System.out.println("Accept");
                   break;
               } else {
                   if(errArray[i]==numArray[j]){
                       System.out.println("Accept");
                       break;
                   }
                   if(errArray[i]>numArray[j]&&errArray[i]<numArray[j+1]){
                       place=j;
                       System.out.println(errArray[i]-numArray[place]);
                   }
               }
            }

        }

    }
}