import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();

        int result;
        result = slidingWindow(a, b);
        sb.append(result);
        System.out.println(sb);

    }

    public static int slidingWindow(String a_str, String b_str) {
        int minDiff = Integer.MAX_VALUE;

        // O((n-m)*m) 시간복잡도
        for (int start = 0; start <= b_str.length() - a_str.length(); start++) {
            int diff = 0;
            for (int i = 0; i < a_str.length(); i++) {
                if (a_str.charAt(i) != b_str.charAt(start+i)) {
                    diff++;
                }
            }
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

}
