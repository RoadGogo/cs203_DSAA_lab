package com.company;

import java.util.Scanner;

public class lab1_solutionOfE {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int times = scanner.nextInt();
        for(int i=0;i<times;i++){
            int n=scanner.nextInt();
            int[] array=new int[n];

            for(int j =0;j<n;j++){
                array[j]=scanner.nextInt();
            }

            int maxDiff=array[n-2]-array[n-1];
            int minNum=array[n-1];
            if(n==2){
                System.out.println(maxDiff);
            }

            if(n>=3) {
                for (int k = n - 1; k > 1; k--) {
                    minNum =  Math.min(minNum,array[k-1]);
                    int diff = array[k - 2] - minNum;
                    if (diff >= maxDiff) {
                        maxDiff = diff;
                    }
                }
                System.out.println(maxDiff);
            }

        }
    }
}
