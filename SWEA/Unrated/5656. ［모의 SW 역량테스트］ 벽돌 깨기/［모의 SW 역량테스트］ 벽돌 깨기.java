import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, W, H;
	static int[] dr = {-1,1,0,0}, dc= {0,0,-1,1};
	static int[][][] grids;
	
	static int shoot(int layer, int r, int c, int removed) {
		int power = grids[layer][r][c];
		grids[layer][r][c]=0;
		removed++;
		
		for(int d=0; d<4; d++) {
			for(int k=1; k<power; k++) {
				int nr=r+dr[d]*k;
				int nc=c+dc[d]*k;
				if(nr<0 || nr>=H || nc<0 || nc>=W || grids[layer][nr][nc]==0) continue;
				removed = shoot(layer, nr, nc, removed);
			}
		}
		return removed;
	}
	
	static void fall(int i) {
		for(int c=0; c<W; c++) { // 폭발 후 fall
			int id = H-1;
			for(int r= H-1; r>=0; r--) {
				if(grids[i][r][c]!=0) {
					grids[i][id][c]=grids[i][r][c];
					if(r!=id) grids[i][r][c]=0;
					id--;
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// input
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			grids = new int[N+1][H][W];
			int total_brick=0;
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					grids[0][i][j]=Integer.parseInt(st.nextToken());
					if(grids[0][i][j]!=0) total_brick++;
				}
			}
			
			
			// simulate
			int answer = total_brick-simulate(0);
			
			// output
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int simulate(int i) {
		if (i == N) return 0;
        
        int max = 0;
        for (int c = 0; c < W; c++) {
            int top = 0;
            while (top < H && grids[i][top][c] == 0) top++;
            if (top >= H) continue; 
            
            for (int r = 0; r < H; r++) {
                System.arraycopy(grids[i][r], 0, grids[i + 1][r], 0, W);
            }
            // shoot & fall
            int removed = shoot(i + 1, top, c, 0);
            fall(i + 1);
            max = Math.max(max, removed + simulate(i + 1));
        }
        return max;
	}
	
}