package BOJ.BOJ_2342_DanceDanceRevolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2342_DanceDanceRevolution {

    static int MAX_LEN;
    static int[] move;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        move = new int[100000];
        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            move[MAX_LEN++] = n;
        }

        if (MAX_LEN == 0) {
            System.out.println(0);
            return;
        } else if (MAX_LEN == 1) {
            System.out.println(2);
            return;
        }
        memo = new int[MAX_LEN][17];
        dfs(1, 0, move[0]);

        for (int i = 0; i < MAX_LEN; i++) {
            System.out.println(Arrays.toString(memo[i]));
        }

        System.out.println(memo[1][move[0]] + 2);
    }


    public static int dfs(int index, int left, int right) {

        if (index == MAX_LEN) {
            return 0;
        }

        if (memo[index][left * 4 + right] != 0) {
            return memo[index][left * 4 + right];
        }

        int result = Integer.MAX_VALUE;
        if (move[index] != left) {
            int min = Integer.min(left, move[index]);
            int max = Integer.max(left, move[index]);
            result = Integer.min(result, dfs(index + 1, min, max) + getPower(right, move[index]));
        }

        if (move[index] != right) {
            int min = Integer.min(move[index], right);
            int max = Integer.max(move[index], right);
            result = Integer.min(result, dfs(index + 1, min, max) + getPower(left, move[index]));
        }

        memo[index][left * 4 + right] = result;

        return result;
    }


    public static int getPower(int from, int to) {
        if (from == to) {
            return 1;
        }

        if (from == 0) {
            return 2;
        }

        if (Math.abs(to - from) == 2) {
            return 4;
        }

        return 3;
    }
}
