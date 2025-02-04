package BOJ.BOJ_12015_가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_12015_가장긴증가하는부분수열2 {

    static int[] arr;
    static ArrayList<Integer> lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        lis = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(arr[0]);
        for (int i = 1; i < N; i++) {
            if (lis.get(lis.size() - 1) < arr[i]) {
                lis.add(arr[i]);
            } else {
                int ret = lower_bound(arr[i]);
                lis.set(ret, arr[i]);
            }
        }
        System.out.println(lis.size());
    }


    private static int lower_bound(int value) {
        int left = 0;
        int right = lis.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (lis.get(mid) < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }


}
