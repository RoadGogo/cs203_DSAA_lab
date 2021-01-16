import java.util.Scanner;

public class lab6_solutionOfB {//nextArray
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        String string = in.next();
        int[] array =new int[string.length()];
        array=nextArray(string);
        for(int a: array){
            System.out.println(a);
        }
    }

    private static int[] nextArray(String string) {
        int m = string.length();
        int[] next =new int[m];
        next[0] = 0;
        int k=0,j=1;
        while(j<m){
            if(string.charAt(j)==string.charAt(k)){
                k=k+1;
                next[j]=k;
                j=j+1;
            }else if(k==0){
                next[j]=0;
                j+=1;
            }else {
                k=next[k-1];
            }
        }
        return next;
    }
}
