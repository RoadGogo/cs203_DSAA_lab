package com.company;

import java.util.Scanner;

public class lab1_solutionOfD {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int times = scanner.nextInt();
        for(int time=0;time<times;time++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int row = c*2+b*2+1;
            int column = b*2 + a*2+1;

            String[][] array =new String[row][column];
            for(int i =0;i<2*b;i++){
                for(int j =0;j<2*b-i;j++){
                    array[i][j]=".";
                }
            }

            for(int i =0;i<2*b;i++) {
                for (int j = 0; j < 2*b - i; j++) {
                    array[row-1-i][column-1-j]=".";
                }
            }

            for(int i=2*b;i<row;i++){
                for(int j=0;j<2*a+1;j++){
                    if(i%2==0&&j%2==0){
                        array[i][j]="+";
                    }
                    if(i%2==1&&j%2==0){
                        array[i][j]="|";
                    }
                    if(i%2==0&&j%2==1){
                        array[i][j]="-";
                    }
                    if(i%2==1&&j%2==1){
                        array[i][j]=".";
                    }
                }
            }

            for(int i=0;i<2*b;i++){
                for(int j=2*b-i;j<column-i;j++){
                    if(i%2==0&&j%2==0){
                        array[i][j]="+";
                    }
                    if(i%2==0&&j%2==1){
                        array[i][j]="-";
                    }
                    if(i%2==1&&j%2==0){
                        array[i][j]=".";
                    }
                    if(i%2==1&&j%2==1){
                        array[i][j]="/";
                    }
                }
            }

            for(int j =2*a;j<column;j++){
                for(int i=2*b-j+2*a;i<row-j+2*a;i++){
                    if(i%2==0&&j%2==0){
                        array[i][j]="+";
                    }
                    if(i%2==0&&j%2==1){
                        array[i][j]=".";
                    }
                    if(i%2==1&&j%2==0){
                        array[i][j]="|";
                    }
                    if(i%2==1&&j%2==1){
                        array[i][j]="/";
                    }
                }
            }

            for(int i=0;i<row;i++){
                for(int j=0;j<column;j++){
                    System.out.print(array[i][j]);
                }
                System.out.println();
            }

        }
    }
}
