package com.company;

import java.util.Scanner;

public class lab2_solutionOfE {
    public static void main(String[] args) {//算出来一个存储一个
        Scanner scanner =new Scanner(System.in);
        int times =scanner.nextInt();
        int[] array = new int[1000001];
        array[0]= 1;
        array[1]= 1;
        array[2]= 1;
        array[3]= 1;
        for(int i=4;i<1000001;i++){
            int mid=i/2;
            array[i]=array[mid]+array[mid-1]+array[mid+1];
        }
        for(int i =0;i<times;i++){
            int n=scanner.nextInt();
            System.out.println(array[n]);
        }
    }
}
