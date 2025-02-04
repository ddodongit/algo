package BOJ.BOJ_1967_트리의지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1967_트리의지름 {


    static ArrayList<Node>[] graph;
    static int maxLength, nodeNum;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        if (n == 1) {
            System.out.println(0);
            return;
        }
        
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, dist));
            graph[to].add(new Node(from, dist));
        }

        visited = new boolean[n + 1];
        maxLength = Integer.MIN_VALUE;
        dfs(1, 0);

        Arrays.fill(visited, false);
        maxLength = Integer.MIN_VALUE;
        dfs(nodeNum, 0);
        System.out.println(maxLength);

    }

    static void dfs(int now, int acc) {
        visited[now] = true;

        for (Node node : graph[now]) {
            if (visited[node.e]) {
                continue;
            }
            dfs(node.e, acc + node.d);
            if (maxLength < acc + node.d) {
                maxLength = acc + node.d;
                nodeNum = node.e;
            }
        }
    }

    static class Node {

        int e, d;

        public Node(int e, int d) {
            this.e = e;
            this.d = d;
        }
    }
}

