package com.company;

import java.util.Scanner;

public class lab2_solutionOfD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int L = scanner.nextInt();
        int max = 0;
        int answer=0;

        int [] dadArray = new int[n+1];
        int [] childArray = new int[n];
        dadArray[n]=L;
        for(int i=0; i<n+1;i++){
            if(i<n) {
                dadArray[i] = scanner.nextInt();
            }
            if(i>=1){
                childArray[i-1]=dadArray[i]-dadArray[i-1];
                if(max<childArray[i-1]){
                    max=childArray[i-1];
                }
            }
        }

        int left = max;int right =L;int mid;
        while (left<=right){
            mid = (left+right)/2;
            if(Judgement(m,childArray,mid)){
                answer=mid;
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        System.out.println(answer);
    }

    public static boolean Judgement(int m,int[]array,int mid){
        int length=1;int sum=0;
        for(int i=0;i<array.length;i++) {
            sum+=array[i];
            if (mid<sum){
                length++;
                sum=array[i];
            }
        }
        if(length >m){
            return false;
        }
        return true;
    }
}
