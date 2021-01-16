import java.util.Scanner;

public class lab6_solutionOfD {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        String[] arr = new String[cases];
        int[] ans = new int[cases];
        for(int i =0;i<cases;i++){
            arr[i] = in.next();
        }
        for(int i =0;i<cases;i++){
            int length = arr[i].length();
            int[] next=nextArray(arr[i]);
            int x = 0;
            while(((length+x)%(length-next[length-1])!=0)){
                x+=1;
            }
            if(next[length-1]==0){
                x=length;
            }
            ans[i] =x;
        }
        for(int i :ans){
            System.out.println(i);
        }

    }

    private static int[] nextArray(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        next[0] = 0;
        int k = 0, j = 1;
        while (j < m) {
            if (pattern.charAt(j) == pattern.charAt(k)) {
                k = k + 1;
                next[j] = k;
                j = j + 1;
            } else if (k == 0) {
                next[j] = 0;
                j += 1;
            } else {
                k = next[k - 1];
            }
        }
        return next;
    }
}
