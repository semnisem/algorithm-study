import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        String a_str = st.nextToken();
        String b_str = st.nextToken();

        int cnt = 0;
        if (a_str.length()==b_str.length()){
            // O(n)으로 차이 카운트
            for (int i = 0; i < a_str.length(); i++) {
                if (a_str.charAt(i)!=b_str.charAt(i)){
                    cnt++;
                }
            }

        } else { // a의 길이 <= b의 길이 0~1 1~2 2~3
            // a_str을 window로 best match 찾기
            int min_mismatch = a_str.length();
            for (int i = 0; i <= b_str.length()-a_str.length(); i++) {
                int mismatch = 0;
                for (int j = 0; j < a_str.length(); j++) {
                    if (a_str.charAt(j)!=b_str.charAt(i+j)){
                        mismatch++;
                    }
                }
                min_mismatch = Math.min(min_mismatch, mismatch);
            }
            cnt = min_mismatch;
        }
        sb.append(cnt);
        System.out.println(sb);

    }

}
