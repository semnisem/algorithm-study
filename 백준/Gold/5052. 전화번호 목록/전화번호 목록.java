import java.io.*;
import java.util.*;

/**
 * 문자열을 다루는 Trie 자료구조
 */


class Node{
	HashMap<Character, Node> children;
	boolean eof;
	
	public Node() {
		children = new HashMap<>();
		eof = false;
	}
}

class Trie{
	Node root;
	
	public Trie() {
		root=new Node();
	}
	
	public boolean insert(String str) {
		Node cur = root;
		for(int i=0; i<str.length(); i++) {
			char c=str.charAt(i);
			if (cur.eof) return false;
			cur.children.putIfAbsent(c, new Node());
			cur=cur.children.get(c);
		}
		cur.eof=true;
		if(!cur.children.isEmpty()) {
			return false;
		}
		return true;
		
	}
}

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			
			// trie 만들기
			String answer = "YES";
			int N = Integer.parseInt(br.readLine());
			Trie trie = new Trie();
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				if(!trie.insert(str))
					answer="NO";
			}
			
			// 결과
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}