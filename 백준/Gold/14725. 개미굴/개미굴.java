import java.io.*;
import java.util.*;

/**
 * 문자열을 다루는 Trie 자료구조
 */


class Node{
	HashMap<String, Node> children;
	boolean eof;
	
	public Node() {
		children = new HashMap<>();
		eof = false;
	}
}

class Trie{
	Node root;
	StringBuilder sb;
	
	public Trie() {
		root=new Node();
		sb = new StringBuilder();
	}
	
	public void preOrder(Node n, int cnt) {
		if(n.eof) return;
		List<String> keySet = new ArrayList<>(n.children.keySet());
        Collections.sort(keySet);
        
		for(String key:keySet) {
			String f = "--".repeat(cnt);
			// System.out.println(f+key);
			sb.append(f).append(key).append("\n");
			preOrder(n.children.get(key), cnt+1);
		}
		
	}
}

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// StringBuilder sb = new StringBuilder();
		
		Trie trie = new Trie();
		ArrayDeque<String> stack = new ArrayDeque<>();
		
		int N = Integer.parseInt(br.readLine());
		for(int signal=1; signal<=N; signal++) {
			
			// 입력
			st=new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			for(int i=0; i<K; i++) {
				String food = st.nextToken();
				stack.offer(food);
			}
			
			// 트리 값 넣기
			Node cur = trie.root;
			String item;
			while(!stack.isEmpty()) {
				item = stack.pop();
				cur.children.putIfAbsent(item, new Node());
				cur = cur.children.get(item);
			}
			cur.eof=true; // leaf

			
		}
		// 트리 중위 순회
		Node cur = trie.root;
		trie.preOrder(cur, 0);
		System.out.print(trie.sb.toString());
	}

}