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

        while (!queue.isEmpty()) { // 예약리스트가 차있는 동안 계속 반복 (목적지에 빨리=짧게 도달이 목표)
            Point p = queue.poll(); // 리스트에서 하나 꺼내고
            int x = p.x, y = p.y, dist=p.dist; // 해당 점의 정보 확인
            // System.out.printf("visit %d, %d -- 거리: %d\n", x, y, dist);

            if (x==N-1 && y==M-1) { // bfs에서 먼저 도착 = 최소 거리
                min_dist=dist;
                break;
            }
            for (int i=0; i<4; i++) { // 그게 아니면 사방 탐색
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx<N && nx>=0 && ny<M && ny>=0 && !visited[nx][ny] && grid[nx][ny]==1){ // 다음 방문지로 적합한지
                    visited[nx][ny]=true; // 방문 미리 해두고 (큐에서 꺼내서 방문하면 중복될 수도..!)
                    queue.add(new Point(nx, ny, dist+1)); // 다음방문지에 추가하기
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