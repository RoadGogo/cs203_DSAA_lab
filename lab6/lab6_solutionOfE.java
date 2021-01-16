import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class lab6_solutionOfE {

    static int base = 139;

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        String s1 = in.next();
        String s2 =in.next();
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int length =Math.min(s1.length(),s2.length());
        System.out.println(longestSub(arr1,arr2,length));
    }

    public static int longestSub(char[] A, char[] B,int length) {
        int left = 1, right = length+1;
        while (left < right) {
            int mid =(left+right)>>1;
//            int mid = left+(right - left)/2;
            if (check(A, B, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    public static boolean check(char[] A, char[] B, int len) {
        long RabinA = 0;long RabinB = 0;
        for (int i = 0; i < len; i++) {
            RabinA = (RabinA * base + A[i]);
        }
        Set<Long> map = new HashSet<Long>();
        //a
        map.add(RabinA);
        long mult = pow(base, len - 1);
        for (int i = len; i < A.length; i++) {
            RabinA = (RabinA - A[i - len] * mult ) * base + A[i];
            map.add(RabinA);
        }
        //b
        for (int i = 0; i < len; i++) {
            RabinB = (RabinB * base + B[i]);
        }
        if (map.contains(RabinB)) {
            return true;
        }
        for (int i = len; i < B.length; i++) {
            RabinB = ((RabinB - B[i - len] * mult ) * base + B[i]);
            if (map.contains(RabinB)) {
                return true;
            }
        }
        return false;
    }

    public static long pow(long mult, long len) {
        long ans = 1;
        while (len != 0) {
            if ((len & 1) != 0) {
                ans = ans * mult;
            }
            mult = mult * mult;
            len >>= 1;
        }
        return ans;
    }
}
