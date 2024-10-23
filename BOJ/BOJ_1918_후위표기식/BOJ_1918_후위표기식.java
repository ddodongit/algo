package BOJ.BOJ_1918_후위표기식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1918_후위표기식 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            if ('A' <= input[i] && input[i] <= 'Z') {
                sb.append(input[i]);
            } else if (input[i] == '(') {
                stack.push(input[i]);
            } else if (input[i] == ')') {
                while (!stack.isEmpty()) {
                    char ch = stack.pop();
                    if (ch == '(') {
                        break;
                    }
                    sb.append(ch);
                }
            } else { // +,-,*,/
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(input[i])) {
                    sb.append(stack.pop());
                }

                stack.push(input[i]);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }

    private static int getPriority(char optr) {
        switch (optr) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return 0;
    }


}
