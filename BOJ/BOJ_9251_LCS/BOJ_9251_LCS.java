package BOJ.BOJ_9251_LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251_LCS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String str2 = br.readLine();

        int[][] dp = new int[str.length()][str2.length()];

        if (str.charAt(0) == str2.charAt(0)) {
            dp[0][0] = 1;
        }

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int j = 1; j < str2.length(); j++) {
            if (str.charAt(0) == str2.charAt(j)) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i < str.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                if (str.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }

        }

        System.out.println(dp[str.length() - 1][str2.length() - 1]);

    }
}