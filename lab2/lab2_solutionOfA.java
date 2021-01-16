package com.company;

import java.math.BigInteger;
import java.util.Scanner;

public class lab2_solutionOfA {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int times =scanner.nextInt();
        for(int i=0;i<times;i++){
            BigInteger n =BigInteger.valueOf(scanner.nextLong());
            BigInteger sum =n.multiply(n.add(BigInteger.valueOf(1)));
            BigInteger sum1=sum.multiply(sum);
            BigInteger sum2=sum1.divide(BigInteger.valueOf(4));
            BigInteger sum3=sum2.mod(BigInteger.valueOf(1000000007));
            System.out.println(sum3);
        }

    }
}
