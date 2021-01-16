package com.company;

import java.util.Scanner;

public class lab2_solutionOfF{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long xr = scanner.nextLong();
        long yr = scanner.nextLong();
        long xc = scanner.nextLong();
        long yc = scanner.nextLong();
        int N = scanner.nextInt();
        String string = scanner.next();
        String[] array = new String[N+1];
        for (int i = 1; i < N+1; i++) {
            array[i] = String.valueOf(string.charAt(i-1));
        }

        long right = (long) Math.pow(10, 15);
        long left = 0;
        long mid = (left + right) / 2;
        Judgement(array,xr,yr,xc,yc,left,right,mid,N);

    }

    public static void Judgement(String[] array, long xr, long yr, long xc, long yc,long left,long right,long mid,int N) {
        long distance=0;
        long answer =-1;
        long xrr=0;long yrr=0;
        while (left<=right) {
             xrr=RobotsxrFinalPosition(mid,N,array,xr,yr);
             yrr=RobotsyrFinalPosition(mid,N,array,xr,yr);
            distance=Math.abs(yrr-yc)+Math.abs(xrr-xc);
            if (distance <= mid) {
                answer =mid;
                right = mid-1 ;
                mid = (left + right) / 2;
                continue;
            } else if (distance > mid) {
                left = mid+1 ;
                mid = (left + right) / 2;
                continue;
            }
        }

        System.out.println(answer);

    }



    public static long RobotsxrFinalPosition(long mid, int N, String[] array, long xr, long yr){
        long times = mid/N;
        long mod = mid%N;
        long x = 0;long y=0;
        for(int i=1;i<array.length;i++){
            if(array[i].equals("U")){
                y+=1;
            }
            if(array[i].equals("D")){
                y-=1;
            }
            if(array[i].equals("L")){
                x-=1;
            }
            if(array[i].equals("R")){
                x+=1;
            }
        }
        x=x*times;y=y*times;
        for(int i=1;i<=mod;i++){
            if(array[i].equals("U")){
                y+=1;
            }
            if(array[i].equals("D")){
                y-=1;
            }
            if(array[i].equals("L")){
                x-=1;
            }
            if(array[i].equals("R")){
                x+=1;
            }
        }
         xr+=x;

        return xr;
    }

    public static long RobotsyrFinalPosition(long mid, int N, String[] array, long xr, long yr){
        long times = mid/N;
        long mod = mid%N;
        long x = 0;long y=0;
        for(int i=1;i<array.length;i++){
            if(array[i].equals("U")){
                y+=1;
            }
            if(array[i].equals("D")){
                y-=1;
            }
            if(array[i].equals("L")){
                x-=1;
            }
            if(array[i].equals("R")){
                x+=1;
            }
        }
        x=x*times;y=y*times;
        if(mod!=0) {
            for (int i = 1; i <= mod; i++) {
                if (array[i].equals("U")) {
                    y += 1;
                }
                if (array[i].equals("D")) {
                    y -= 1;
                }
                if (array[i].equals("L")) {
                    x -= 1;
                }
                if (array[i].equals("R")) {
                    x += 1;
                }
            }
        }
        yr+=y;
        return yr;
    }

}
