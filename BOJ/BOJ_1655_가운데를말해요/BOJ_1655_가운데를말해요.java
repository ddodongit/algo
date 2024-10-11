package BOJ.BOJ_1655_가운데를말해요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1655_가운데를말해요 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());

            maxHeap.add(num);

            if (!minHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
                minHeap.add(maxHeap.poll());
            }

            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            }
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            System.out.println(maxHeap.peek());
        }

    }
}
