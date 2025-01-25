import java.util.*;
import java.io.*;

public class Main {
	public static int sliding_window(String inputStr) {
		int result=0;
		int cnt_a=0, min_a = inputStr.length();
		int cnt_b=0, min_b = inputStr.length();;
		for (int i=0; i<inputStr.length(); i++) {
			char c = inputStr.charAt(i);
			if (c=='a') {
				cnt_a+=1;
			}
			else {
				cnt_b+=1;
			}
		}
		for (int i=0; i<inputStr.length()-cnt_a; i++) { // a window
			String sub = inputStr.substring(i, i+cnt_a);
			int temp = 0; // window 내 b의 개수
			for (int j=0; j<sub.length(); j++) {
				if (sub.charAt(j)=='b')
					temp+=1;
			}
			min_a = Math.min(min_a, temp);
		}
		for (int i=0; i<inputStr.length()-cnt_b; i++) { // b window
			String sub = inputStr.substring(i, i+cnt_b);
			int temp = 0; // window 내 a의 개수
			for (int j=0; j<sub.length(); j++) {
				if (sub.charAt(j)=='a')
					temp+=1;
			}
			min_b = Math.min(min_b, temp);
		}
		result = Math.min(min_a,  min_b);
		
		return result;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int min_swap=sliding_window(br.readLine());
		
		sb.append(min_swap);
		System.out.println(sb.toString());
		br.close();
		
	}

}