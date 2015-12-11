package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CC150ch9RecursionAndDP {
  //9.1 leetcode climb steps leetcode
  //9.2 leetcode robot travel from (0,0) to (m,n), can only go right or down; follow up if having obstacle
  //9.3 find magic index of a sorted array with distinct numbers, magic index i is A[i]==i
	//binary search
	public static int magicIndex(int[] A){
		int low=0,high=A.length-1;
		while(low<=high){
			int mid=low+(high-low)/2;
			if(A[mid]==mid) return mid;
			if(A[mid]<mid) low=mid+1;
			else high=mid-1;
		}
		return -1;
	}
  //9.3.2 follow up if allow duplicates, still do recursive binary search with modification
	public static int magicIndexDup(int[] A){
		int low=0,high=A.length-1;
		return search(A,low, high);
	}
	private static int search(int[]A, int low, int high){
		if(low<0||high>=A.length||low>high) return -1;
		int mid=low+(high-low)/2;
		if(A[mid]==mid) return mid;
		int i=search(A,low,Math.min(A[mid], mid-1));
		if(i>=0) return i;
		return search(A,Math.max(A[mid], mid+1),high);
	}
	//9.4 leetcode subset of a int array
	//9.5 all permutations of a string, leetcode is permute of a int array
	public static List<String> permute(String s){
		List<String> res=new ArrayList<>();
		if(s==null) return res; 
		HashSet<Character> visited=new HashSet<>();
		dfs(s,visited,"",res);
		return res;
	}
	private static void dfs(String s, HashSet<Character> visited,String path,List<String> res){
		if(s.length()==path.length()) {
			res.add(path);
			return;
		}
		for(int i=0;i<s.length();++i){
			char c=s.charAt(i);
			if(!visited.contains(c)){
				visited.add(c);
				//path+=c;//wrong if add c here
				dfs(s,visited,path+c,res); //cannot do path+=c out of the function call
				visited.remove(c);
			}
		}	
	}
	//9.6 leetcode, print n pairs of parentness
	//9.7 given a 2d array, paint a pointer(x,y) and surrounding area with same color to another color walmart lab
	enum Color{
		Black,White,Red,Yellow,Green;
	}
	public boolean paintFill(Color[][] screen,int x, int y, Color color){
		if(screen[y][x]==color) return false;
		return (paintFill(screen,x,y,screen[y][x],color));//note x is horizontal xle, y is vertical xle
	}
	private boolean paintFill(Color[][] screen, int x,int y, Color original,Color newColor){
		if(x<0||x>=screen[0].length||y<0||y>=screen.length) return false;
		if(screen[y][x]==original){
			screen[y][x]=newColor;
			paintFill(screen,x-1,y,original,newColor);
			paintFill(screen,x+1,y,original,newColor);
			paintFill(screen,x,y-1,original,newColor);
			paintFill(screen,x,y+11,original,newColor);
		}
		return true;
	}
	//9.8
	//given unlimited 25c,10c,5c,1c, find ways to sum up as n cents
	static int count=0;
	public static int countWays(int n){
		if(n<=0) return -1;
		int[] arr={1,5,10,25};
		dfs(n,arr, 0);	
		return count;
	}
	private static void dfs(int n,int[] A,int start){
		if(n==0){
			count++;
			return;
		}
		for(int i=start;i<A.length&&n>=A[i];++i){
			dfs(n-A[i],A,i);
		}
	}
	//if we need to get all possible combinations not just count
	public static List<List<Integer>> coinsPath(int n){
		List<List<Integer>> res=new ArrayList<>();
		if(n<=0) return res;
		int[] arr={1,5,10,25};
		dfs(n,arr,0,new ArrayList<Integer>(),res);
		return res;
	}
	private static void dfs(int n, int[] arr, int start, List<Integer> path, List<List<Integer>> res){
		if(n==0) {
			res.add(new ArrayList<>(path));
			return;
		}
		for(int i=start;i<arr.length&&n>=arr[i];++i){
			path.add(arr[i]);
			dfs(n-arr[i],arr,i,path,res);
			path.remove(path.size()-1);
		}
		
	}
	
	
	//9.9 leetcode 8 queens
	//9.10 ??highest box stacks??
	//9.11 ??count ways to evaluate a logic expression with different parenthesis, expression only consist of 0,1,& | ^
	
	public static void main(String[] args){
		//9.8 count nicle ways
		System.out.println(countWays(10));
		System.out.println(coinsPath(10));
		//9.4 string permutation
		System.out.println(permute("abc"));
		
		//9.3 find magic index
		int[] A={-40,-20,-1,1,2,3,5,7,9,12,13};
		int[] B={-40,-20,-1,3,3,3,5,7,9,12,13};
		System.out.println(magicIndex(A));
		System.out.println(magicIndexDup(B));
	}
	
}
