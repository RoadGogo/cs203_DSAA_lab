package com.company;

import java.util.Scanner;

public class lab2_solutionOfC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long s = scanner.nextLong();
        long[] array = new long[n];
        long ways = 0;

        for(int i=0;i<n;i++) {
            array[i] = scanner.nextLong();
        }
        if(s<array[0]) {
            System.out.println(0);
            return;
        }

        for(int i=0;i<n;i++) {
            long sum= s - array[i];
            int j =i+1;
            int k =n-1;
            int j0;

            while(j<k) {
                long num1=1;long num2=1;long num3=1;
                if (array[j] + array[k] < sum) {
                    j ++;
                    continue;
                }
                if (array[j] + array[k] > sum) {
                    k --;
                    continue;
                }
                if(array[j]+array[k]==sum&&array[j]!=array[k]){
                    j0=j;
                    for(int num=j;num<k;num++){
                        if(array[num]==array[num+1]){
                            num1++;
                            j=num+1;
                        }else {
                            break;
                        }
                    }
                    for(int num=k;num>=j0;num--){
                        if(array[num]==array[num-1]){
                            num2++;
                            k = num - 1;
                        }else {
                            break;
                        }
                    }
                    ways=ways+num1*num2;
                    k--;
                    continue;
                }
                if(array[j]+array[k]==sum&&array[j]==array[k]){
                    for(int num=j;num<k;num++){
                        if(array[num]==array[num+1]){
                            num3++;
                        }
                    }
                    ways=ways+num3*(num3-1)/2;
                    break;
                }
            }

        }
        System.out.println(ways);

    }
}

//        for (int i = 0; i < n; i++) {
//            array[i] = scanner.nextInt();
//        }
//        if (s < array[0]) {
//            System.out.println(0);
//            return;
//        }
//        //7 7 1 2 2 3 4 4 6这种情况
//        for (int i = 0; i < n; i++) {
//            long road = s- array[i];
//            int j = i + 1;
//            int k = n - 1;
//            int place=0;
//            while (j < k) {
//
//                if(array[i]+array[j]<road){
//                    j++;
//                }
//                if(array[i]+array[j]>road){
//                    k--;
//                }
//                if(array[i]+array[j]==road){
//                    if(array[])
//                }
//
//

//                if (array[i] + array[j] + array[k] < s) {
//                    j++;
//                }
//                if (array[i] + array[j] + array[k] > s) {
//                    k--;
//                }
//                if (array[i] + array[j] + array[k] == s) {
//                    sum++;
//                    k--;
//                    if (j <= k) {
//                        j++;
//                        k = n - 1;
//                    }
//                }
//            }
//        }
//        System.out.println(sum);
//
//    }
//}


//        for(int i =0;i<n;i++){
//            array[i]=scanner.nextInt();
//        }
//        if(s<= array[0]){
//            System.out.println(000);
//        }else if(array[n-1]>s){
//            int place=0;
//            for(int i= n-1;i>0;i--){
//               if(array[i-1]<s&&s<array[i]){
//                   place=i;
//                   break;
//               }
//            }
//            for(int i=0;i<place;i++){
//                for(int j =i+1;j<place-1;j++){
//                    for(int k=i+2;k<place-2;k++){
//                        if(array[i]+array[j]+array[k]==s) {
//                            sum++;
//                        }
//                    }
//                }
//            }
//            System.out.println(sum+"   02");
//        } else {
//            for(int i=0;i<n;i++){
//                for(int j =i+1;j<n-1;j++){
//                    for(int k=j+1;k<n-2;k++){
//                       sum++;
//                    }
//                }
//            }
//            System.out.println(array[0]+array[1]+array[2]);
//            System.out.println(s);
//            System.out.println(sum+"  03");
//        }