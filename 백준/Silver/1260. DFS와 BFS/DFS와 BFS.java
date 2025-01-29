import java.io.*;
import java.util.*;

public class Main {

    private static List<List<Integer>> adjList;

    public Main(int n){
        adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int v, int w){
        adjList.get(v).add(w);
        adjList.get(w).add(v);
    }

    public void sortAdjList() {
        for (List<Integer> list : adjList) {
            Collections.sort(list);  // 각 리스트(노드의 인접 리스트)를 오름차순으로 정렬
        }
    }

    // 그래프 출력 (디버깅 용)
    public void printGraph() {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print(i + ": ");
            for (int neighbor : adjList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public static List<Integer> dfs(int start, ArrayList<Integer> visited) {
        if (visited.contains(start)) {
            return visited;
        }

        visited.add(start);
        for (int neighbor : adjList.get(start)) {
            dfs(neighbor, visited);
        }
        return visited;
    }


    public static List<Integer> bfs(int start, ArrayList<Integer> visited){
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int neighbor : adjList.get(v)) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                visited.add(neighbor);
                queue.add(neighbor);
            }
        }

        return visited;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st=new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점 <= 10^3
        int M = Integer.parseInt(st.nextToken()); // 간선을 이루는 두 정점 (양방향) <= 10^4
        int V = Integer.parseInt(st.nextToken()); // 탐색 시작점

        // 그래프 생성
        Main graph = new Main(N+1);
        for(int i=0; i<M; i++){
            st=new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.addEdge(v, w);
        }
        graph.sortAdjList();
        // graph.printGraph();

        // 탐색
        List<Integer> dfs=dfs(V, new ArrayList<>());
        for (int num: dfs){
            sb.append(num).append(" ");
        }
        sb.append("\n");
        List<Integer> bfs=bfs(V ,new ArrayList<>());
        for (int num: bfs){
            sb.append(num).append(" ");
        }
        System.out.println(sb);

    }
}
