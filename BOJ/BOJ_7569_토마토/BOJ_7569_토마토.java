package BOJ.BOJ_7569_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {

    static int N, M, H, emptyCnt = 0, count = 0;
    static int[][][] box;
    static int[] di = {-1, 0, 1, 0, 0, 0},
        dj = {0, 1, 0, -1, 0, 0},
        dk = {0, 0, 0, 0, 1, -1}; // 뒤, 오, 앞, 왼, 상, 하
    static HashSet<int[]> ripe = new HashSet<>();


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    box[k][i][j] = Integer.parseInt(st.nextToken());
                    if (box[k][i][j] == -1) {
                        continue;
                    }

                    if (box[k][i][j] == 1) {
                        ripe.add(new int[]{k, i, j});
                    } else if (box[k][i][j] == 0) {
                        box[k][i][j] = Integer.MAX_VALUE;
                        emptyCnt++;
                    }
                }
            }
        }

        if (emptyCnt == 0) {
            System.out.println(0);
            return;
        }

        for (int[] point : ripe) {
            bfs(point[0], point[1], point[2]);
        }

        if (count != emptyCnt) {
            System.out.println(-1);
        } else {
            int result = Integer.MIN_VALUE;
            for (int k = 0; k < H; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (box[k][i][j] == -1) {
                            continue;
                        }
                        if (result < box[k][i][j]) {
                            result = box[k][i][j];
                        }
                    }
                }
            }
            System.out.println(result - 1);
        }
    }

    private static void bfs(int startK, int startI, int startJ) {

        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(startK);
        queue.add(startI);
        queue.add(startJ);

        while (!queue.isEmpty()) {
            int nowK = queue.poll();
            int nowI = queue.poll();
            int nowJ = queue.poll();

            for (int d = 0; d < 6; d++) {
                int nextK = nowK + dk[d];
                int nextI = nowI + di[d];
                int nextJ = nowJ + dj[d];

                if (isOutOfBounds(nextK, nextI, nextJ)) {
                    continue;
                }
                if (box[nextK][nextI][nextJ] == -1) {
                    continue;
                }
                if (box[nextK][nextI][nextJ] <= box[nowK][nowI][nowJ] + 1) {
                    continue;
                }

                queue.add(nextK);
                queue.add(nextI);
                queue.add(nextJ);
                if (box[nextK][nextI][nextJ] == Integer.MAX_VALUE) {
                    count++;
                }
                box[nextK][nextI][nextJ] = box[nowK][nowI][nowJ] + 1;
            }
        }

    }

    private static boolean isOutOfBounds(int k, int i, int j) {
        return
            k < 0 || k > H - 1
                || i < 0 || i > N - 1
                || j < 0 || j > M - 1;
    }
    
}
