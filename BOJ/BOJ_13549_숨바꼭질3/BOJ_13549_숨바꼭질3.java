package BOJ.BOJ_13549_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13549_숨바꼭질3 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dijkstra(N, K);

    }

    private static void dijkstra(int N, int K) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.d, o2.d);
            }
        });

        int[] dist = new int[100001];

        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Node(N, 0));
        dist[N] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.num == K) {
                break;
            }

            if (now.num - 1 >= 0 && dist[now.num - 1] > dist[now.num] + 1) {
                dist[now.num - 1] = dist[now.num] + 1;
                pq.add(new Node(now.num - 1, now.d + 1));
            }
            if (now.num + 1 <= 100000 && dist[now.num + 1] > dist[now.num] + 1) {
                dist[now.num + 1] = dist[now.num] + 1;
                pq.add(new Node(now.num + 1, now.d + 1));
            }
            if (now.num * 2 <= 100000 && dist[now.num * 2] > dist[now.num]) {
                dist[now.num * 2] = dist[now.num];
                pq.add(new Node(now.num * 2, now.d));
            }
        }

        System.out.println(dist[K]);
    }


    private static class Node {

        int num, d;

        public Node(int num, int d) {
            this.num = num;
            this.d = d;
        }
    }
}
