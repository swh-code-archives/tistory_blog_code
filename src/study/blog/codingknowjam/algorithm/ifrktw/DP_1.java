package study.blog.codingknowjam.algorithm.ifrktw;

import java.util.Scanner;

public class DP_1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] dp = new int[N+1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[N]);
    }

}

