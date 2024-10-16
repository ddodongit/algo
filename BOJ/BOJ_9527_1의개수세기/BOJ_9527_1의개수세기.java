package BOJ.BOJ_9527_1의개수세기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9527_1의개수세기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(acc_sum(B) - (acc_sum(A - 1)));
    }

    private static long acc_sum(long num) {
        long sum = 0;
        long pos = 1L;

        while (pos <= num) {
            long quotient = num / (pos << 1);
            long remain = num % (pos << 1);

            sum += quotient * pos + Math.max(0, remain - pos + 1);

            pos = pos << 1;
        }

        return sum;

    }
}
