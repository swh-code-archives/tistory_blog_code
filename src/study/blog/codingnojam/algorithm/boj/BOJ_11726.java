package study.blog.codingnojam.algorithm.boj;

import java.util.Scanner;

// 백준온라인저지 11726번 2xn 타일링 문제 풀이
public class BOJ_11726 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if (N == 1) {
            System.out.println(1);
            return;
        }
        long[] dp = new long[N+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2])%10007;
        }
        System.out.println(dp[N]);
    }
}
