package BOJ.BOJ_1238_파티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        ArrayList<Edge>[] reversed = new ArrayList[N + 1];
        int[] dist = new int[N + 1];
        int[] reversed_dist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
            reversed_dist[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<Edge>();
            reversed[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[s].add(new Edge(e, t));
            reversed[e].add(new Edge(s, t));
        }

        dijkstra(graph, dist, X);
        dijkstra(reversed, reversed_dist, X);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE || reversed_dist[i] == Integer.MAX_VALUE) {
                continue;
            }
            if (dist[i] + reversed_dist[i] > answer) {
                answer = dist[i] + reversed_dist[i];
            }
        }
        System.out.println(answer);
    }

    private static void dijkstra(ArrayList<Edge>[] graph, int[] dist, int X) {

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        pq.add(new Edge(X, 0));
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (now.t > dist[now.e]) {
                continue;
            }

            for (Edge edge : graph[now.e]) {
                int next = edge.e;

                if (dist[next] > dist[now.e] + edge.t) {
                    dist[next] = dist[now.e] + edge.t;
                    pq.add(new Edge(next, dist[next]));
                }
            }
        }

    }

    static class Edge implements Comparable<Edge> {

        int e, t;

        public Edge(int e, int t) {
            this.e = e;
            this.t = t;
        }

        @Override
        public int compareTo(Edge o) {
            return this.t - o.t;
        }

    }

}

