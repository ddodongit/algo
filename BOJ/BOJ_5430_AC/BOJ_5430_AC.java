package BOJ.BOJ_5430_AC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class BOJ_5430_AC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            arr = arr.substring(1, arr.length() - 1);
            ArrayDeque<String> deque = new ArrayDeque<>();
            if (n > 0) {
                deque.addAll(Arrays.asList(arr.split(",")));
            }
            boolean reversed = false;
            boolean isError = false;
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == 'R') {
                    reversed = !reversed;
                } else if (p.charAt(i) == 'D') {
                    if (deque.isEmpty()) {
                        isError = true;
                        sb.append("error\n");
                        break;
                    } else {
                        if (reversed) {
                            deque.pollLast();
                        } else {
                            deque.pollFirst();
                        }
                    }
                }
            }
            if (!isError) {
                sb.append("[");

                if (reversed) {
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollLast());
                        if (!deque.isEmpty()) {
                            sb.append(",");
                        }
                    }
                } else {
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollFirst());
                        if (!deque.isEmpty()) {
                            sb.append(",");
                        }
                    }
                }
                sb.append("]\n");
            }
        }
        System.out.println(sb);
    }
}
