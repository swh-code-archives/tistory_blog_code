package study.blog.codingnojam.algorithm.programmers;

import java.util.ArrayList;
import java.util.List;

public class 위클리챌린지_9주차_전력망을둘로나누기 {
    static boolean[] checkTop;

    public int solution(int n, int[][] wires) {
        checkTop = new boolean[n + 1];
        ArrayList<Top> topList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            topList.add(new Top(i));
        }
        for (int i = 0; i < wires.length; i++) {
            topList.get(wires[i][0]).connectTops.add(new Top(wires[i][1]));
            topList.get(wires[i][1]).connectTops.add(new Top(wires[i][0]));
        }

        for (int i = 0; i < wires.length; i++) {
            for (int j = 0; j < topList.get(wires[i][0]).connectTops.size(); j++) {
                if (topList.get(wires[i][0]).connectTops.get(j).index == wires[i][1]) {

                }
            }
            topList.get(wires[i][1]).connectTops.add(new Top(wires[i][0]));
        }


        int answer = -1;
        return answer;
    }

    class Top {
        int index;
        ArrayList<Top> connectTops = new ArrayList<>();

        Top(int index) {
            this.index = index;
        }
    }
}
