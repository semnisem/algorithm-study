import java.io.*;
import java.util.*;

public class Main {
	static int N, M, cleanCnt;
	static int[][] room;
	static int[] dr = new int[]{-1,0,1,0}, dc = new int[]{0,1,0,-1};
	
	static void clean(int r, int c, int d) {
		// 현위치 청소하기
		if(room[r][c]==0) {
			room[r][c]=2;
			++cleanCnt;
		}
		// 주변 탐색하기
		boolean isClean = true;
		for(int i=0; i<4; i++) {
			int nr=r+dr[i];
			int nc=c+dc[i];
			if(nr>=0 && nc>=0 && nr<N && nc<M && room[nr][nc]==0) {
				isClean=false;
				break;
			}
		}
		
		// 탐색 결과에 따라
		if(!isClean) {
			d=(d+3)%4; // 반시계 방향으로 90도 회전
			int nr=r+dr[d];
			int nc=c+dc[d];
			if(nr>=0 && nc>=0 && nr<N && nc<M) {
				if(room[nr][nc]==0) {
					clean(nr, nc, d);
				}
				else {
					clean(r, c, d);
				}
			}
		}
		else {
			// 후진
			int nr=r-dr[d];
			int nc=c-dc[d];
			if(nr>=0 && nc>=0 && nr<N && nc<M && room[nr][nc]!=1) {
				clean(nr, nc, d);
			}
			else {
				return;
			}
		}
		return;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// StringBuilder sb = new StringBuilder();
			
		// 입력
		st = new StringTokenizer(br.readLine());			
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];			
		
		st = new StringTokenizer(br.readLine());
		int r= Integer.parseInt(st.nextToken());
		int c= Integer.parseInt(st.nextToken());
		int d= Integer.parseInt(st.nextToken()); // 0~3 시계방향
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				room[i][j]=Integer.parseInt(st.nextToken()); // 0:청소안된빈칸 1:벽	2:청소완료
			}
		}
		
		// 알골
		cleanCnt=0;
		clean(r, c, d);

		System.out.println(cleanCnt);
	}
}