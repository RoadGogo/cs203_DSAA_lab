package com.company;

import java.math.BigInteger;
import java.util.Scanner;

public class lab1_solutionOfA{

   public static void main(String[]args){
       Scanner sca=new Scanner(System.in);
        int times =sca.nextInt();

        for(int i=0;i<times;i++){
            int n =sca.nextInt();
            long result=1;
            for(int j=0;j<n;j++) {
                result = (result * 3) % 1000000007;
            }
            result=result-1;
            System.out.println(result);
        }
   }
}

