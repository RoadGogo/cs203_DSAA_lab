import java.util.Scanner;

public class lab6_solutionOfA {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int nameLength = in.nextInt();
        String[] array = new String[nameLength];
        for(int i =0; i< nameLength;i++){
            array[i] = in.next();
        }
        int ans =Integer.MAX_VALUE;
        for(int i=1;i<nameLength;i++){
            int a = getPrefix(array[0],array[i]);
            int b = getSuffix(array[0],array[i]);
            ans= Math.min(ans,a*b);
        }
        System.out.println(ans);
    }

    private static int getSuffix(String s, String s1) {
        int ans =0;
        for(int i =0;i<s.length()&&i<s1.length();i++){
            if(!String.valueOf(s.charAt(i)).equals(String.valueOf(s1.charAt(i)))){
                break;
            }else {
                ans++;
            }
        }
        return ans;
    }

    private static int getPrefix(String s, String s1) {
        int ans =0;
        for(int i =s.length()-1,j=s1.length()-1;i>=0&&j>=0;i--,j--){
            if(!String.valueOf(s.charAt(i)).equals(String.valueOf(s1.charAt(j)))){
                break;
            }else {
                ans++;
            }
        }
        return ans;
    }
}
