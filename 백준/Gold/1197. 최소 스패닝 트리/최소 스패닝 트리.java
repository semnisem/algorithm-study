import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int to, weight;
	Node next;
	
	public Node(int to, int weight, Node next) {
		this.to=to;
		this.weight=weight;
		this.next=next;
	}
	
	@Override
	public String toString() {
		return "Node[to="+to+", weight="+weight+", next="+next+"]";
	}
	
	
	@Override
	public int compareTo(Node n) {
		return this.weight-n.weight; // weight기준 오름차순
	}
}

public class Main{
	
	static int V, E;
	static Node[] graph;
	
	static int prim(int start, int cost) {
		boolean[] visited=new boolean[V+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		Node root = new Node(start, 0, graph[start]);
		visited[0]=true;
		pq.offer(root);
		
		while(!pq.isEmpty()) {
			Node now = pq.poll(); 
			//System.out.println("visit: "+now.to); 
			if(visited[now.to]) continue; // 방문된 거면 pass
			visited[now.to]=true; // 방문처리 및 하고 싶은 거
			
			cost+=now.weight;
			
			for(Node target=graph[now.to]; target!=null; target=target.next) {
				if(visited[target.to]) {
					//System.out.println(target.to+": pass");
					continue;
				}
				
				pq.offer(target); // 반문하지 않은 거면 넣기
				//System.out.println(target.to+": add");
			}
		}
		return cost;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// StringBuilder sb = new StringBuilder();
		
		// 입력
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		graph=new Node[V+1];
		E = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int weight=Integer.parseInt(st.nextToken());
			graph[a]=new Node(b, weight, graph[a]);
			graph[b]=new Node(a, weight, graph[b]);
		}
//		for(Node node:graph)
//			System.out.println(node);
		
		// 출력
		System.out.println(prim(1,0));

	} // main
} // public class