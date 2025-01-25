import java.util.*;
import java.io.*;

public class Main {
	public static int cal_person(int h, int w, int n, int m) {
		
		int vertical = h/(n+1);
		if (h%(n+1)>0) {
			vertical+=1;
		}
		int horizontal = w/(m+1);
		if (w%(m+1)>0) {
			horizontal+=1;
		}
		
		return vertical*horizontal;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st=new StringTokenizer(br.readLine(), " ");
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int max_person=cal_person(h,w,n,m);

		sb.append(max_person);
		System.out.println(sb.toString());
		br.close();
		
	}

}
