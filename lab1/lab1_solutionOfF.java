package com.company;

import java.util.Scanner;

public class lab1_solutionOfF {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int times=scanner.nextInt();
        for(int i=0;i<times;i++) {
            int n =scanner.nextInt();
            int [] array =new int[n];
            int Nim_sum;
            Nim_sum=Integer.valueOf("0",2);

            for(int j=0;j<n;j++){
                array[j]=scanner.nextInt();
            }
            for(int k =0;k<n;k++){
                Nim_sum^=Integer.valueOf(Integer.toBinaryString(array[k]),2);

            }

            if(Nim_sum==0){
                System.out.println(0);
            }else {
                int ways=0;
                for(int m=0;m<n;m++){
                    if(Integer.valueOf(Integer.toBinaryString(array[m]),2).compareTo(Integer.valueOf(Integer.toBinaryString(array[m]),2)^Nim_sum)>=0){
                        ways++;
                    }
                }
                System.out.println(ways);
            }

        }
    }
}