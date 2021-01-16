import java.util.Scanner;

public class lab6_solutionOfC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ans = 0;
        int arrLength = in.nextInt();
        String[] stringArr = new String[arrLength + 1];
        for (int i = 1; i <= arrLength; i++) {
            stringArr[i] = in.next();
        }
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int index1 = in.nextInt();
            int index2 = in.nextInt();
            String judge = in.next();
            if (Search(stringArr[index1], stringArr[index2], judge)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static boolean Search(String pattern, String text, String judge) {
        int length = Math.min(pattern.length(), text.length());
        String string1 = pattern + text;
        String string2 = text + pattern;
        int index1 = nextArray(string1)[string1.length()-1];
        int index2 = nextArray(string2)[string1.length()-1];

        if(index1>length){
            int i=0;
            while (i<pattern.length()){
                String start = string1.substring(0,i+1),end = string1.substring(string1.length() - i);
                if(!start.equals(end)){
                    index1 = 1+i;
                    break;
                }
                i++;
            }
        }
        if(index2>length){
            int i=0;
            while (i<text.length()){
                String start = string2.substring(0,i+1),end = string2.substring(string2.length() - i);
                if(!start.equals(end)){
                    index2 = 1+i;
                    break;
                }
                i++;
            }
        }
        if((index1==index2&&judge.equals("="))||(index1>index2&&judge.equals(">"))||(index1<index2&&judge.equals("<"))){
            return true;
        }

        return false;
    }

    private static int[] nextArray(String string) {
        int m = string.length();
        int[] next = new int[m];
        next[0] = 0;
        int k = 0, j = 1;
        while (j < m) {
            if (string.charAt(j) == string.charAt(k)) {
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
