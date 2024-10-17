package BOJ.BOJ_7662_이중우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_7662_이중우선순위큐 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2, o1);
                }
            });
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            HashMap<Integer, Integer> count = new HashMap<>(); // n, cnt
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());

                if (cmd == 'I') {
                    maxHeap.add(n);
                    minHeap.add(n);
                    count.put(n, count.getOrDefault(n, 0) + 1);
                } else if (cmd == 'D') {
                    if (n == 1) {
                        while (!maxHeap.isEmpty()) {
                            int max = maxHeap.poll();
                            if (count.containsKey(max)) {
                                if (count.get(max) - 1 <= 0) {
                                    count.remove(max);
                                } else {
                                    count.replace(max, count.get(max) - 1);
                                }

                                break;
                            }
                        }

                    } else if (n == -1) {
                        while (!minHeap.isEmpty()) {
                            int min = minHeap.poll();
                            if (count.containsKey(min)) {
                                if (count.get(min) - 1 <= 0) {
                                    count.remove(min);
                                } else {
                                    count.replace(min, count.get(min) - 1);
                                }
                                break;
                            }
                        }
                    }
                }

            }

            if (count.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                while (!maxHeap.isEmpty()) {
                    int max = maxHeap.poll();
                    if (count.containsKey(max)) {
                        sb.append(max).append(" ");
                        break;
                    }
                }
                while (!minHeap.isEmpty()) {
                    int min = minHeap.poll();
                    if (count.containsKey(min)) {
                        sb.append(min).append("\n");
                        break;
                    }
                }
            }
        }
        System.out.println(sb);
    }

}
