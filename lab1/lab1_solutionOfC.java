package com.company;

import java.util.Scanner;

public class lab1_solutionOfC {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int times =scanner.nextInt();
        for(int i=0;i<times;i++){
            int m=scanner.nextInt();
            int n=scanner.nextInt();
            if(m*n==1){
                System.out.println("Bob");
            }else {
                System.out.println("Alice");
            }
        }
    }
}
