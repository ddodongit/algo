package BOJ.BOJ_2098_외판원순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098_외판원순회 {

    static int N;
    static int[][] map, dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        dp = new int[N + 1][1 << N];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(1, 1));

        for (int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

    }


    private static int dfs(int now, int visitBit) {

        if (visitBit == (1 << N) - 1) {
            return map[now][1] == 0 ? 1000000000 : map[now][1];
        }

        if (dp[now][visitBit] != -1) {
            return dp[now][visitBit];
        }

        dp[now][visitBit] = 1000000000;

        for (int next = 1; next <= N; next++) {
            int checkBit = 1 << (next - 1);
            if ((visitBit & checkBit) != 0 || map[now][next] == 0) {
                continue;
            }
            int result = dfs(next, visitBit | (1 << (next - 1))) + map[now][next];
            if (dp[now][visitBit] > result) {
                dp[now][visitBit] = result;
            }
        }

        return dp[now][visitBit];

    }
}
