package algorithms;

public class WordDictionary {
	private TrieNode root;
    public WordDictionary(){
        root=new TrieNode();
    }
    // Adds a word into the data structure.
    public void addWord(String word) { //o(word.length)
        if(word==null||word.length()==0) return;
        TrieNode p=root;
        for(int i=0;i<word.length();++i){
            char c=word.charAt(i);
            if(!p.next.containsKey(c)){
                p.next.put(c,new TrieNode());
            }
            p=p.next.get(c);
        }
        p.isEnd=true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) { //o(word.len)
        if(word==null||word.length()==0) return false;
        return search(word,root);
    }
    private boolean search(String word, TrieNode root){
        if(root==null) return false;
        if(word.length()==0) return root.isEnd;
        char c=word.charAt(0);
        if(root.next.containsKey(c)){
           return search(word.substring(1),root.next.get(c));
        }else if(c=='.'){
            if(root.next.isEmpty()) return false;
            for(TrieNode n:root.next.values()){
                if(search(word.substring(1),n)) return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
    	WordDictionary solution=new WordDictionary();
    	solution.addWord("abc");
    	System.out.println(solution.search("..d"));
    }
}
