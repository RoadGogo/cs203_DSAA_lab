package com.company;

import java.util.Scanner;

public class lab2_solutionOfG {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int times =scanner.nextInt();
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int M = scanner.nextInt();
        int answer=0;
        int[] array =new int[N+1];
        int[] newArray =new int[N+1];
        for(int i=0;i<times;i++){
            for(int j=0; j<N;j++){
                array[j]=scanner.nextInt();
                newArray[j]=array[j];
            }
            for(int k=0;k<N;k++){
                for(int m =k+1;m<N+1;m++){
                    if (newArray[k]>newArray[m]){
                        int temp= newArray[k];
                        newArray[k]=newArray[m];
                        newArray[m]=temp;
                    }
                }
            }

            int left =0;int right= N-1;int mid;
            while (left<right){
                mid=left+(left-right+1)/2;
                if(judgement(mid,array,N,K,M)){
                    answer=newArray[mid];
                    left=mid;
                }else {
                    right=mid-1;
                }
            }
            System.out.println(answer);
        }
    }

    public static boolean judgement(int mid,int[] array,int N,int K,int M){
        long numOfArray=0;//ans
        long numOfBiggerThanMid=0;//num
        int left =0;
        int right =-1;
//        numOfArray=(N-K+2)*(N-K+1)/2;
        while (right<N){
            if(array[right+1]>=mid){
                numOfBiggerThanMid++;
                right++;
            }else {
                if(numOfBiggerThanMid==K){
                    numOfArray+=(N-right);
                }
                if(array[left]>=mid)numOfBiggerThanMid--;
                left++;
                if(numOfArray>=M){
                    return true;
                }
            }
        }
        return false;

    }
}
