import java.io.*;
import java.util.*;

public class Main {
    final static int MAX_RAIN = 100;
    private static int N;
    private static int[][] grid;
    // 상우하좌
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static boolean[][] visited;
    public static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx>=0 && ny>=0 && nx<N && ny<N && grid[nx][ny]>0 && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        visited = new boolean [N][N];
        int max_safezone = 1;

        for (int rain = 1; rain <= MAX_RAIN; rain++) {
            // visited 초기화
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            // 비 내리는 양 up -> 잠기기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (grid[r][c] <= rain) {
                        grid[r][c] = 0;
                    }
                }
            }

            // 영역 개수 카운팅
            int cnt = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (grid[r][c] != 0 && !visited[r][c]) {
                        dfs(r,c);
                        cnt++;
                    }
                }
            }
            // 영역 최대 개수 update
            max_safezone = Math.max(max_safezone, cnt);
            // System.out.printf("강수량: %d -- 영역 개수: %d\n", rain, cnt);
        }
        // 정답 출력
        sb.append(max_safezone).append("\n");
        System.out.println(sb);
    }
}