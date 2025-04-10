import java.io.*;
import java.util.*;

public class Solution{
	
	static int N, W, H, max_removed=0;
	static int[] dr = {-1,1,0,0}, dc= {0,0,-1,1};
	static int[][][] grids;
	static int[][] org;
	static ArrayList<int[]> arr_list;
	
	static void permutate(int cnt, int[] arr) {
		if(cnt==N) {
			int[] a = arr.clone();
			arr_list.add(a);
			return;
		}
		
		for(int i=0; i<W; i++) {
			arr[cnt]=i;
			permutate(cnt+1, arr);
		}
	}
	
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
			
			org = new int[H][W];
			grids = new int[N+1][H][W];
			int total_brick=0;
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					org[i][j]=Integer.parseInt(st.nextToken());
					if(org[i][j]!=0) total_brick++;
				}
			}
			
			// permute
			arr_list = new ArrayList<>();
			permutate(0, new int[N]);
			
			// shoot & fall
			int max_removed = 0;
			for(int[] shoot_list: arr_list) {
				for (int y = 0; y < H; y++) {
			        System.arraycopy(org[y], 0, grids[0][y], 0, W);
			    }
				int removed=0;
				
				for(int i=0; i<N; i++) {
					for (int y = 0; y < H; y++) {
			            System.arraycopy(grids[i][y], 0, grids[i + 1][y], 0, W);
			        }
					int col = shoot_list[i];
					int row = 0;
					while(row<H && grids[i+1][row][col]==0) row++;
					if(row>=H) continue;
					
					removed+=shoot(i+1, row, col, 0); // 폭발
					for(int c=0; c<W; c++) { // 폭발 후 fall
						ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
						for(int r=0; r<H; r++) {
							if(grids[i + 1][r][c]!=0) {
								stack.offer(grids[i+1][r][c]);
								grids[i + 1][r][c]=0;
							}
						}
						int r=H-1;
						while(!stack.isEmpty()) {
							grids[i + 1][r--][c]=stack.pollLast();
						}
					}
						
				}
				max_removed=Math.max(removed, max_removed);
			}
			// output
			sb.append("#").append(tc).append(" ").append(total_brick-max_removed).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}