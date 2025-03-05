import java.io.*;
import java.util.*;

public class Main {
	
	static int R, C, maxPipe;
	static int[] dy = new int[]{-1, 0, 1}; // 앞, 위대각선, 아래대각선
	static int[][] map;
	
	public static boolean dfs(int x, int y) {
		// System.out.println("x:"+x+" y:"+y);
		if(x==C-1) { // 도착 - 성공
			// System.out.println("도착");
			maxPipe++;
			return true;
		}
		
		for(int d=0; d<3; d++) {
			int nx=x+1;
			int ny=y+dy[d];
			if(ny>=0 && ny<R && nx<C && map[ny][nx]==0) {
				map[ny][nx]=1;
				if(dfs(nx, ny)) {
					return true;
				}
					
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=1; j<C-1; j++) {
				char c = str.charAt(j);
				if(c=='x') {
					map[i][j]=1;
				}
			}
		}

		maxPipe=0;
		for(int start_y=0; start_y<R; start_y++) {
			// System.out.println(Arrays.deepToString(map));
			map[start_y][0]=1;
			dfs(0, start_y);
		}
		System.out.println(maxPipe);
	}
}