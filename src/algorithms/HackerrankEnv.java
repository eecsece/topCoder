package algorithms;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class HackerrankEnv {
	static class ScoreNode{
		int score;
		int count;
		ScoreNode left;
		ScoreNode right;
		public ScoreNode(int score){
			this.score=score;
			this.count=1;
		}
	}
	static Map<Integer,ScoreNode> map=new HashMap<>();
	public static String gatherScore(int[] scores){
		ScoreNode root=new ScoreNode(scores[0]);
		map.put(scores[0],root);
		createTree(scores,root);
		return bsf(root);
		
	}
	private static String bsf(ScoreNode head) {
		
		return null;
	}
	private static void createTree(int[] scores,ScoreNode root){
	
		for(int i=1;i<scores.length;++i){
			int score=scores[i];
			if(map.containsKey(score)) map.get(score).count++;
			else {
				ScoreNode node=new ScoreNode(score);
				
				map.put(score, node);
			}
		}
		
	}

	public static void print(int n){
		
		
	    for(int i=0;i<n;++i){
	    	//int spaces=6;
	        int spaces=n-i-1;
	        int stars=i+1;
	        while(spaces>0){
	            System.out.print(" ");
	            spaces--;
	        }
	        while(stars-->0){
	            System.out.print("#");
	        }
	        System.out.println();
	    }
	}    
    public static void main(String[] args) {
    	
    	
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//        Scanner sc=new Scanner(System.in);
//        int target=sc.nextInt();
//        int N=sc.nextInt();
//        int[] nums=new int[N];
//        for(int i=0;i<N;++i){
//            nums[i]=sc.nextInt();
//        }
//    	print(6);
    }
}
