package BOJ.BOJ_1043_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1043_거짓말 {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());

        if (cnt == 0) {
            System.out.println(M);
            return;
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= cnt; i++) {
            int num = Integer.parseInt(st.nextToken());
            parents[num] = 0;
        }

        Queue<ArrayList<Integer>> queue = new ArrayDeque<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            ArrayList<Integer> list = new ArrayList<>();
            int a = Integer.parseInt(st.nextToken());
            list.add(a);
            if (cnt > 1) {
                for (int j = 1; j < cnt; j++) {
                    int b = Integer.parseInt(st.nextToken());
                    list.add(b);
                    union_find(a, b);
                }
            }
            queue.add(list);
        }

        int party = 0;
        while (!queue.isEmpty()) {
            boolean flag = true;
            for (Integer num : queue.poll()) {
                if (getParents(num) == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                party++;
            }
        }

        System.out.println(party);

    }

    private static void union_find(int a, int b) {

        int pa = getParents(a);
        int pb = getParents(b);

        if (pa == pb) {
            return;
        }

        if (pa < pb) {
            parents[pb] = pa;
        } else {
            parents[pa] = pb;
        }

    }

    private static int getParents(int n) {

        if (parents[n] != n) {
            parents[n] = getParents(parents[n]);
        }

        return parents[n];
    }

}
