import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7579_앱 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] m = new int[N + 1];
        int[] c = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        int maxCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            maxCost += c[i];
        }

        int[] dp = new int[maxCost + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = maxCost; j >= c[i]; j--) {
                int included = dp[j - c[i]] + m[i];
                if (dp[j] < included) {
                    dp[j] = included;
                }
            }
        }

        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}