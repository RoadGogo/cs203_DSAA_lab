import java.util.Scanner;

public class lab5_solutionOfA {
    //本次方法用数数的方法做的，课后应该在写一下栈的做法
    static int count1Left;
    static int count1Right;
    static int count2Left;
    static int count2Right;
    static int count3Left;
    static int count3Right;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseTimes = scanner.nextInt();
        for (int times = 0; times < caseTimes; times++) {
            int length = scanner.nextInt();
            String string = scanner.next();
            ini();
            if (compare(string, length)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void ini() {
        count1Left = 0;
        count1Right = 0;
        count2Left = 0;
        count2Right = 0;
        count3Left = 0;
        count3Right = 0;
    }

    public static boolean compare(String string, int length) {
        for (int i = 0; i < length; i++) {
            if (count1Left < count1Right || count2Left < count2Right || count3Left < count3Right) {
                return false;
            } else {
                if (String.valueOf(string.charAt(i)).equals("{")) {
                    count1Left++;
                    continue;
                } else if (String.valueOf(string.charAt(i)).equals("[")) {
                    count2Left++;
                    continue;
                } else if (String.valueOf(string.charAt(i)).equals("(")) {
                    count3Left++;
                    continue;
                } else if (String.valueOf(string.charAt(i)).equals("}")) {
                    count1Right++;
                    continue;
                } else if (String.valueOf(string.charAt(i)).equals("]")) {
                    count2Right++;
                    continue;
                } else {
                    count3Right++;
                    continue;
                }
            }
        }

        if (count1Left == count1Right && count2Left == count2Right && count3Left == count3Right) {
            return true;
        } else {
            return false;
        }
    }


}
