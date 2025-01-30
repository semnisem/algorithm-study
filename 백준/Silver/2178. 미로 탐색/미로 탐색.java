import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;

    static class Point{
        int x, y, dist;

        Point(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static int bfs(int[][] grid) {
        int[] dx={0,1,0,-1};
        int[] dy={-1,0,1,0};
        int min_dist=M*N;

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        queue.add(new Point(0,0,1)); // x,y, dist
        visited[0][0]=true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x, y = p.y, dist=p.dist;
            // System.out.printf("visit %d, %d -- 거리: %d\n", x, y, dist);

            if (x==N-1 && y==M-1) {
                min_dist=dist; // bfs에서 먼저 도착 = 최소 거리
                break;
            }

            for (int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx<N && nx>=0 && ny<M && ny>=0 && !visited[nx][ny] && grid[nx][ny]==1){
                    visited[nx][ny]=true;
                    queue.add(new Point(nx, ny, dist+1));
                }
            }
        }
        return min_dist;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // target 입력 (N행 M열)
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // map 입력
        int[][] map = new int[N][M];
        for (int i=0; i<N; i++) {
            String row = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = row.charAt(j)-'0';
            }
        }
        // print_grid(map);

        // 최소 거리 구하는 알고리즘
        int min_cost = bfs(map);

        sb.append(min_cost);
        System.out.println(sb);
    }
}