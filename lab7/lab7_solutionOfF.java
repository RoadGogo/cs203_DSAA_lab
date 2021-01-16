import java.io.*;
import java.util.*;
//Wa:when friendNum==1 return is false
//also it can use fast I/O
public class lab7_solutionOfF {
    public static class node {
        int num;
        boolean hasPeople = false;
        ArrayList<node> child;

        node(int num) {
            this.num = num;
            child = new ArrayList<>();
        }

        mult<node, Integer> findTheLongestCity(node start, int distance) {
            mult<node, Integer> ans;
            if (this.hasPeople) {
                ans = new mult<>(this, distance);
            } else {
                ans = new mult<>(null, -1);
            }
            for (node node : child) {
                if (node == start) {
                    continue;
                }
                mult<node, Integer> temp = node.findTheLongestCity(this, distance + 1);
                if (temp.ansInt() > ans.ansInt()) {
                    ans = temp;
                }
            }
            return ans;
        }
    }

    public static class mult<node, Int> {
        private node node;
        private Int anInt;

        public mult(node node, Int anInt) {
            this.node = node;
            this.anInt = anInt;
        }

        public node ansNode() {
            return node;
        }

        public Int ansInt() {
            return anInt;
        }
    }


    public static void main(String[] args)  {
        Scanner in =new Scanner(System.in);
        int caseTimes = in.nextInt();
        while (caseTimes>0) {
            int cityNum = in.nextInt();
            int friendNum = in.nextInt();
            node[] cityList = new node[cityNum];
            int[] friendList = new int[friendNum];
            //creat cityList
            for (int i = 0; i < cityNum; i++) {
                cityList[i] = new node(i);
            }
            for (int i = 0; i < cityNum - 1; i++) {
                //creat tree
                int city1 = in.nextInt() - 1;
                int city2 = in.nextInt() - 1;
                cityList[city1].child.add(cityList[city2]);
                cityList[city2].child.add(cityList[city1]);
            }
            for (int i = 0; i < friendNum; i++) {
                //friend in the num city
                int num = in.nextInt() - 1;
                friendList[i] = num;
                cityList[num].hasPeople = true;
            }
            if (friendNum == 1) {
                System.out.println(0);
                caseTimes--;
                continue;
            }
            node from = cityList[0].findTheLongestCity(null, 0).ansNode();
            int distance = from.findTheLongestCity(null, 0).ansInt();
            int ans = (int) ((distance + 1) / 2);
            System.out.println(ans);
            caseTimes--;
        }
    }


}