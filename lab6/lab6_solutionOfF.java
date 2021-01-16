import java.util.Arrays;
import java.util.Scanner;

public class lab6_solutionOfF {//超时原因是因为翻译时用的字符串拼接，每次都要new一个新的字符串出来，导致超时
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        char[] replaceArr = new char[26];
        for(int i =0;i<26;i++) {
            replaceArr[i]=in.next().charAt(0);
        }
        String encryption = in.next();
        char[] encryptionArr=encryption.toCharArray();
        char[] original = decode(encryptionArr,replaceArr);

        System.out.println(Search(original,encryptionArr));

    }

    private static char[] decode(char[] encryption, char[] replaceArr) {
        char[] ans = new char[encryption.length];
        char[] oriArr ={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for(int i =0;i<encryption.length;i++){
            char data =encryption[i];
            for(int j =0;j<26;j++){
                if(data==replaceArr[j]){
                    ans[i] = oriArr[j];
                }
            }
        }
        return ans;
    }
    private static int[] nextArray(char[] pattern) {
        int m = pattern.length;
        int[] next = new int[m];
        next[0] = 0;
        int k = 0, j = 1;
        while (j < m) {
            if (pattern[j] == pattern[k]) {
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

    private static int Search(char[] pattern, char[] text) {
        int length = pattern.length;
        char[] string1 = new char[length+length];//String string1 = pattern + text;
        for(int i=0;i< string1.length;i++){
            if (i<length){
                string1[i] = pattern[i];
            }else {
                string1[i] = text[i-length];
            }
        }
        int index1 = nextArray(string1)[string1.length-1];

        if(index1>length){
            int i=0;
            while (i<pattern.length){
                char[] start1 = Arrays.copyOfRange(string1,0,i-1);
                char[] end1 = Arrays.copyOfRange(string1,string1.length-i,string1.length);
                String start = start1.toString(),end = end1.toString();
                if(!start.equals(end)){
                    index1 = 1+i;
                    break;
                }
                i++;
            }
        }
        return length-index1;
    }

}