import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static boolean[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생의 수
        M = Integer.parseInt(st.nextToken());

        // 그래프 저장 DB - N*N
        grid = new boolean[N][N];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int smaller=Integer.parseInt(st.nextToken())-1;
            int taller=Integer.parseInt(st.nextToken())-1;
            grid[smaller][taller]=true;
        }

        int cnt = algo();
        System.out.println(cnt);
    }

    static int algo(){
        int cnt=0;

        Queue<Integer> queue = new ArrayDeque<>();

        for(int node=0; node<N; node++) {
            boolean[] visited = new boolean[N];
            visited[node]=true;

            // 그래프 BFS 탐색 - 상위
            queue.offer(node);
            while(!queue.isEmpty()) {
                int r = queue.poll();
                for(int c=0; c<N; c++) {
                    if(grid[r][c] && !visited[c]) {
                        queue.offer(c);
                        visited[c]=true;
                    }
                }
            }

            // 그래프 BFS 탐색 - 하위
            queue.offer(node);
            while(!queue.isEmpty()) {
                int c = queue.poll();
                for(int r=0; r<N; r++) {
                    if(grid[r][c] && !visited[r]) {
                        queue.offer(r);
                        visited[r]=true;
                    }
                }
            }

            int visitNodes=0;
            for (boolean v: visited){
                if(v) visitNodes++;
            }
            if(visitNodes==N) cnt++;
        }
        return cnt;
    }
}