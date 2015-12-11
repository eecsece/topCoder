package algorithms;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	 boolean isEnd;
	 Map<Character,TrieNode> next;//use hashmap is easier for check '.'
	 public TrieNode() {
	      isEnd=false;
	      next=new HashMap<>();
	 }
}
