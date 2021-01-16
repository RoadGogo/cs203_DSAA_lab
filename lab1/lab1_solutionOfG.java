package com.company;

import java.util.Scanner;

public class lab1_solutionOfG {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int times =scanner.nextInt();
        for(int i =0; i<times;i++){
            String n =scanner.next();
            int s = scanner.nextInt();
            int sum =0;int place=0;int sumbefore;
            for(int j=0;j<n.length();j++){
                int a = n.charAt(j)-48;//-48并不出错
                sumbefore=sum;
                sum +=a;
                if(sum >= s&& sumbefore<s){//=的位置可能有变动
                    place=j;
                }
            }
            if(sum<=s){
                System.out.println(0);
            }else{
                String subn=n.substring(place);
//                System.out.println(n);
//                System.out.println(place);
//                System.out.println(subn);
                System.out.println((long)Math.pow(10,n.length()-place)-Long.valueOf(subn).longValue());
            }

        }

    }
}
