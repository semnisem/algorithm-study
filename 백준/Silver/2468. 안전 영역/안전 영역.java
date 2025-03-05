import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] dx=new int[]{0,1,0,-1} , dy=new int[]{-1,0,1,0};
	static int[][] pollen;
	static boolean[][] allergy; 
	
	static void dfs(int x, int y) {
		for(int d=0; d<4; d++) {
			int nx = x+dx[d]; // 열
			int ny = y+dy[d]; // 행
			if(nx<0 || ny<0 || nx>=N || ny>=N || allergy[ny][nx]) continue;
			allergy[ny][nx]=true;
			dfs(nx, ny);
		}
		return;
	}
	public static void main(String[] args) throws Exception{
		// System.setIn(new FileInputStream("2번_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력
		N = Integer.parseInt(br.readLine());
		pollen = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				pollen[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 알고리즘
		int max_cnt = 1;
		for(int deg=0; deg<100; deg++) { // deg이하일 때 알러지 발생
			
			// allergy 발생 여부 처리
			allergy = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(pollen[i][j]<=deg) {
						allergy[i][j]=true;
					}
				}
			}
			
			// false인 영역 수 계산하기
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if (allergy[i][j]) continue;
					dfs(j,i);
					cnt++;
				}
			}
			
			// 최대 영역 수 update
			max_cnt = Math.max(max_cnt, cnt);
		}
		// 출력
		System.out.println(max_cnt);
	}

}