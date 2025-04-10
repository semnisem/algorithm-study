import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, W, H, max_removed=0;
	static int[] dr = {-1,1,0,0}, dc= {0,0,-1,1};
	static int[][] grid, org;
	
	static int[] arr;
	static ArrayList<int[]> arr_list;
	
	static void permutate(int cnt) {
		if(cnt==N) {
			int[] a = arr.clone();
			arr_list.add(a);
			return;
		}
		
		for(int i=0; i<W; i++) {
			arr[cnt]=i;
			permutate(cnt+1);
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
			
			int total_brick=0;
			org = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					org[i][j]=Integer.parseInt(st.nextToken());
					if(org[i][j]!=0) total_brick++;
				}
			}
			
			// arr_list (shooting 열)
			arr = new int[N];
			arr_list = new ArrayList<>();
			permutate(0);
			
			// get answer
			int max_removed = 0;
			grid = new int[H][W];
			for(int[] shoot_list: arr_list) {
				int removed=0;
				for(int i=0; i<H; i++) {
					grid[i]=Arrays.copyOf(org[i], W);
				}
                
				for(int j: shoot_list) {
					 // (i,j)에 있는 거 쏘기 (제거)
					int i=0;
					while(i<H && grid[i][j]==0) i++;
					if(i>=H) continue;
					removed+=shoot(i,j, 0);
					
					// 아래로 내리기
					for(int c=0; c<W; c++) {
						ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
						for(int r=0; r<H; r++) {
							if(grid[r][c]!=0)
								stack.offer(grid[r][c]);
							grid[r][c]=0;
						}
						int r=H-1;
						while(!stack.isEmpty()) {
							grid[r--][c]=stack.pollLast();
						}
					}
				}
				max_removed=Math.max(removed, max_removed);
			}
			sb.append("#").append(tc).append(" ").append(total_brick-max_removed).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int shoot(int r, int c, int removed) {
		int power = grid[r][c];
		grid[r][c]=0;
		removed++;
		
		for(int d=0; d<4; d++) {
			for(int k=1; k<power; k++) {
				int nr=r+dr[d]*k;
				int nc=c+dc[d]*k;
				if(nr<0 || nr>=H || nc<0 || nc>=W || grid[nr][nc]==0) continue;
				removed = shoot(nr, nc, removed);
			}
		}
		return removed;
	}

}