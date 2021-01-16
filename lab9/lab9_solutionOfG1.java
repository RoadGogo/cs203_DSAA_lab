import java.io.*;
import java.util.*;
//form github
public class lab9_solutionOfG1 {
    public static int n;
    public static long[] dp;
    public static LinkedList<Integer>[] adj;
    public static int[] a;
    public static int[] b;

    public static void main(String[] args){
        Scanner in =new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int times = in.nextInt();
        for (int time = 0; time < times; time++) {
            n = in.nextInt();
            int m = in.nextInt();
            adj = new LinkedList[n + 1];
            for (int i = 1; i <= n; i++) {
                adj[i] = new LinkedList<>();
            }
            dp = new long[n + 1];
            a = new int[n + 1];
            b = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
                b[i] = in.nextInt();
            }
            for (int i = 1; i <= m; i++) {
                int start = in.nextInt();
                int end = in.nextInt();
                adj[start].add(end);
            }

            long ans = 0;
            for (int i = 1; i <= n; i++) {
                ans = (ans + DP(i) * a[i] % 1000000007) % 1000000007;
            }
            out.println((1000000007 + ans) % 1000000007);
        }
        out.close();
    }

    public static long DP(int i) {
        if (dp[i] != 0) return dp[i];
        long ans = 0;
        for (Integer j : adj[i]) {
            long it = DP(j);
            if (!adj[j].isEmpty()) {
                ans = (ans + it) % 1000000007;
            }
            ans = (ans + b[j]) % 1000000007;
        }
        dp[i] = ans;
        return ans;
    }

}