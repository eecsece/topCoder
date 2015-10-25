package test;

public class KMP {
    public static int[] getNext(String pattern){
    	int j = 0;
    	int[] failure=new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
          while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) { 
            j = failure[j - 1];
          }
          if (pattern.charAt(j) == pattern.charAt(i)) { 
            j++; 
          }
          failure[i] = j;
        }
        return failure;
    }
    
    public static int[] getNext2(String pattern){
    	int m=pattern.length();
    	int[] next=new int[m];
    	next[0]=-1;
    	int j=0,k=-1;
    	while(j<m-1){
    		if(k==-1 || pattern.charAt(k)==pattern.charAt(j)){
    			j++;k++;
    			next[j]=k;
    		}else{
    			k=next[k];
    		}
    	}
    	return next;
    	
    }
    
    public static int match(String source, String pattern){
    	int j = 0;  
    	int[] failure=getNext(pattern);
        for (int i = 0; i < source.length(); i++) {
          while (j > 0 && pattern.charAt(j) != source.charAt(i)) {
            j = failure[j - 1];
          }
          if (pattern.charAt(j) == source.charAt(i)) { j++; }
          if (j == pattern.length()) {
            return i - pattern.length() + 1;
            
          }
        }
        return -1;
    }
    
    public static void main(String[] args){
    	int[] a=getNext2("abcabcd");
    	for(int i:a){
    		System.out.print(i+" ");
    	}
    	System.out.println();
    	int[] b=getNext("abcabcd");
    	for(int i:b){
    		System.out.print(i+" ");
    	}
    	
    }
}
