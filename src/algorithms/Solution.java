package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;


public class Solution extends Thread {
	
	
	
	
	
	public String convertDecimalToBin(int d){
		StringBuilder sb=new StringBuilder();
		while(d!=0){
			sb.append(d%2);
			d/=2;
		}
	    return sb.reverse().toString();	
	}
	
	public void findPopularNum(int[] A){
		int i=0,N=A.length;
		while(i<3*N/4){
			int j=i+N/4;
			if(A[j]!=A[i]) i++;
			else{
				System.out.print(A[i]+" ");
				do{j++;}while(j<N&&A[i]==A[j]);
				i=j;
			}
		}
	}
	
	public boolean possibleSum(int[] A, int target){
		if(A==null||A.length==0) return false;
		return dfs(A,0,target);
	}
	private boolean dfs(int[] A, int start, int target){
		if(target==0) return true;
		for(int i=0;i<A.length&&A[i]<=target;++i){
			if(dfs(A,i,target-A[i])) return true;
		}
		return false;
	}
	public int hammingWeight(int n) {
        int count=0;
        while(n!=0){
            if((n&1)!=0) count++;
            n>>>=1;
        }
        return count;
    }
	
	public int countSubarray(int[] A){
		if(A==null||A.length<3) return 0;
		int res=0;
		for(int i=0;i<A.length-2;++i){
			int diff=A[i+1]-A[i];
			int count=2;
			for(int j=i+2;j<A.length;++j){   		
				if(A[j]-A[j-1]!=diff) break;
				count++;
				if(count>=3) res++;	
		    }
		}
		return res;
	}
	
		public int minSubArrayLen(int s, int[] nums) {
	        if(nums==null||nums.length==0) return 0;
	        int minLen=Integer.MAX_VALUE;
	        int[] sums=new int[nums.length+1];
	        for(int i=1;i<sums.length;++i) sums[i]=sums[i-1]+nums[i-1];
	        for(int i=1;i<sums.length;++i){
	            if(sums[i]>=s){
	              int beforePos=bs(sums,0,i,sums[i]-s+1);
	              if(beforePos!=-1) minLen=Math.min(minLen,i);
	            }
	        }
	        if(minLen==Integer.MAX_VALUE) return 0;
	        return minLen;
	    }
	    private int bs(int[] sums,int start,int end,int target){
	        while(start<=end){
	            int mid=(start+end)/2;
	            if(sums[mid]==target) return mid;
	            else if(sums[mid]<target) start=mid+1;
	            else end=mid-1;
	        }
	        if(sums[start]<target) return start;
	        if(sums[end]<target) return end;
	        return -1;
	    }  
	    public static int minSemester(int[][] req){
	    	int n=req.length;
	    	int max=0;
	    	List[] graph=new ArrayList[n];
	    	int[] indegree=new int[n];
	    	for(int i=0;i<n;++i){
	    		//graph[req[i][1]].add(req[i][0]);
	    		indegree[req[i][0]]++;
	    	}
	    	for(int in:indegree){
	    		max=Math.max(max,in);
	    	}
	    	return max+1;
	    }
	    
	    public int[] findOrder(int numCourses,int[][] prerequisites){
	    	int[] res=new int[numCourses];
	    	ArrayList[] graph=new ArrayList[numCourses];
	    	for(int i=0;i<numCourses;++i){
	    		graph[i]=new ArrayList();
	    	}
	    	for(int i=0;i<prerequisites.length;++i){
	    		graph[prerequisites[i][1]].add(prerequisites[i][0]);
	    	}
	    	boolean[] visited=new boolean[numCourses];
	    	boolean[] inStack=new boolean[numCourses];
	    	Stack<Integer> stack=new Stack<>();
	    	for(int i=0;i<numCourses;++i){
	    		if(!dfs(i,visited,graph,stack,inStack)){
	    			return new int[0];
	    		}
	    	}
	    	for(int i=0;i<numCourses;++i){
	    		res[i]=stack.pop();
	    	}
	    	return res;	    		    	
	    }
	    private boolean dfs(int course,boolean[] visited, ArrayList[] graph,Stack<Integer> stack,boolean[] inStack){
	        if(visited[course]) return false;
	    	visited[course]=true;
	    	for(Object u:graph[course]){
	    		if(!dfs((int)u,visited,graph,stack,inStack))
	    				return false;
	    	}
	    	if(!inStack[course])stack.push(course);
	    	inStack[course]=true;
	    	visited[course]=false;
	    	return true;
	    }
	    
	    
	    public List<List<Integer>> combinationSum3(int k, int n) {
	        List<List<Integer>> res=new ArrayList<>();
	        dfs(1,k,n,new ArrayList<Integer>(),res);
	        return res;
	    }
	    private void dfs(int start,int count,int target,List<Integer> path, List<List<Integer>> res ){
	        if(target==0 && count==0){
	            res.add(new ArrayList(path));
	            return;
	        }
	        for(int i=start;i<=9 && count>0 && target>0;++i){
	            path.add(i);
	            dfs(i+1,count-1,target-i,path,res);  
	            path.remove(path.size()-1);
	        }     
	    }
	    public List<int[]> getSkyline(int[][] buildings) {
	        List<int[]> res=new ArrayList<>();
	        if(buildings==null||buildings.length==0) return res;
	        Comparator<Building> c=new Comparator<Building>(){
	            public int compare(Building b1,Building b2){
	                if(b1.l!=b2.l) return b1.l-b2.l;
	                if(b1.h!=b2.h) return b1.h-b2.h;
	                if(b1.r!=b2.r) return b1.r-b2.r;
	                else return 0;    
	            }
	        };
	        //PriorityQueue<Building> heap=new PriorityQueue<>(c);
	        Building[] list=new Building[buildings.length];
	        for(int i=0;i<buildings.length;++i){
	            //heap.add(new Building(buildings[i][0],buildings[i][1],buildings[i][2]));
	        	list[i]=(new Building(buildings[i][0],buildings[i][1],buildings[i][2]));
	        }
	        Arrays.sort(list,c);
	        int maxR=list[0].r,maxH=list[0].h,minL=list[0].l;
	        for(int i=1;i<buildings.length;++i){
	        	int l=list[i].l, r=list[i].r, h=list[i].h;
//	        	if(l!=minL) {
//	        		res.add(new int[]{minL,maxH});
//	        		minL=l;
//	        		if(l>maxR){
//		        		res.add(new int[]{maxR,0});
//		        		maxH=h;
//		        		maxR=r;
//		        		continue;
//		        	};
//	        		if(h<=maxH) minL=Math.max(l,list[i-1].r); 
//	        	}
	        	if(l>minL && r>list[i-1].r){
	        		res.add(new int[]{minL,maxH});
	        		minL=Math.max(l,list[i-1].r);
	        	}
	        	maxH=Math.max(h,maxH);
	        	maxR=Math.max(r,maxR);   	
	        	
	        }
	        res.add(new int[]{minL,maxH});
	        res.add(new int[]{maxR,0});
	        for(int[] r:res){
	        	System.out.print(r[0]+" "+r[1]);
	        	System.out.println();
	        }
	        return res;
	    }
	    
	    
	    public List<Integer> diffWaysToCompute(String input) {
	    	if(input==null||input.length()==0) return new ArrayList<Integer>();
	        Map<String,List<Integer>> cache=new HashMap<>();
	        return helper(input,cache);
	    }
	    
	    private List<Integer> helper(String s, Map<String,List<Integer>> cache){
	    	if(cache.containsKey(s)) return cache.get(s);
	    	List<Integer> res=new ArrayList<>();
	    	for(int i=0;i<s.length();++i){
	    		char c=s.charAt(i);
	    		if(c=='+'||c=='-'||c=='*'){
		    		List<Integer> left=helper(s.substring(0,i),cache);
		    		List<Integer> right=helper(s.substring(i+1),cache);
		    		for(Integer l:left){
		    			for(Integer r:right){
		    				res.add(calc(l,r,c));
		    			}
		    		}
	    		}
	    	}
	    	if(res.size()==0) res.add(Integer.valueOf(s));
	    	cache.put(s, res);
	    	return res;
	    }
	    
	    private int calc(int l, int r, char op){
	    	int res=0;
	    	switch(op){
	    	  case '+': res=l+r; break;
	    	  case '-': res=l-r; break;
	    	  case '*': res=l*r; break;
	    	  default:break;
	    	}
	    	return res;
	    }
	    public int[] productExceptSelf(int[] nums) {
	        //o(n):o(1)
	        //it tells you n>1 so we dont need to do data validation
	        int n=nums.length;
	        int[] res=new int[n];
	        for(int i=0,temp=1;i<n;++i){
	            res[i]=temp;
	            temp*=nums[i];
	        }
	        for(int i=n-1,temp=1;i>=0;--i){
	            res[i]*=temp;
	            temp*=nums[i];
	        }
	        return res;
	    }
	    
	    //correct order
	    public void correctOrder(int[] nums){
	    	if(nums==null||nums.length<=1) return;
	    	int i=0,j=nums.length-1;
	    	while(i<nums.length-1 && nums[i]<=nums[i+1]) i++;
	    	if(i==nums.length-1) return; //corner case: already sorted
	    	while(j>=1 && nums[j]>=nums[j-1]) j--;
	    	int temp=nums[i];
	    	nums[i]=nums[j];
	    	nums[j]=temp;
	    	
	    }
	     
	    public void nextPermutation(int[] nums) {
	        //step 1 scan from length-2 to 0, find first i to make nums[i]<nums[i+1]
	        //step 2 if i>=0 after step 1, find first j from i+1 to end which make nums[j]<=nums[i]; --j; swap(i,j)
	        //step 3 reverse from i+1 to j
	        //o(n):o(1)
	        if(nums==null||nums.length==0) return;
	        int i=nums.length-2;
	        while(i>=0 && nums[i]>=nums[i+1]) {--i;}
	        if(i>=0){
	          int j=i+1;
	          while(j<nums.length && nums[j]>nums[i]) {++j;}
	          j--;
	          swap(nums,i,j);
	        }
	        reverse(nums,i+1,nums.length-1);
	    }
	    private void swap(int[] nums, int i, int j){
	        int temp=nums[i];
	        nums[i]=nums[j];
	        nums[j]=temp;
	    }
	    private void reverse(int[] nums, int start,int end){
	        while(start<end){
	            int temp=nums[start];
	            nums[start++]=nums[end];
	            nums[end--]=temp;
	        }
	    }
	    
	   
	    public List<String> binaryTreePaths(TreeNode root) {
			List<String> res=new ArrayList<>();
			if(root==null) return res;
			helper(root,"",res);
			return res;
			
		}
		private void helper(TreeNode root,String path,List<String> res){
			if(root==null) return;
			if(root.left==null&&root.right==null){
				res.add(path);
				return;
			}
			
			helper(root.left,path+root.val+"->",res);
			
			helper(root.right,path+root.val+"->",res);
			
			
		}    
		public int nthUglyNumber(int n) {
	        if(n<1) return -1;
			int index2=0,index3=0,index5=0, res=1;
			int[] buf=new int[n];
			buf[0]=1;
			for(int i=1;i<n;++i){
				int v2=2*buf[index2], v3=3*buf[index3], v5=5*buf[index5];
				res=Math.min(v2,Math.min(v3,v5));
				buf[i]=res;
				if(res==v2) index2++;
				if(res==v3) index3++;
				if(res==v5) index5++;		
			}
			return res;
	    }
		public int divide(int dividend, int divisor) {
	        assert(divisor!=0);
	        if(divisor==1 || dividend==0) return dividend;
	        if(dividend==Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
	        boolean isNeg=(dividend<0 && divisor>0) || ( dividend>0 && divisor<0);
	        long a=Math.abs((long)dividend), b=Math.abs((long)divisor);
	        if(a<b) return 0;
	        long res=0;
	        while(a>=b){
	           long div=b, multiplier=1;
	           while(a>=div+div){
	               div+=div;
	               multiplier+=multiplier;
	           }
	           res+=multiplier;
	           a-=div;
	        }
	       
	        return isNeg? (int)(0-res):(int)res;
	    }
	     
		private static final int maxDiv10=Integer.MAX_VALUE/10;
	    public int atoi(String str) {
	        //part1 dealing with leading whitespaces and +/-
	        if(str==null ||str.length()==0) return 0;
	        int i=0, n=str.length();
	        while(i<n && Character.isWhitespace(str.charAt(i))) i++;//leading spaces
	        int sign=1;
	        if(i<n && str.charAt(i)=='+'){
	            ++i;
	        }else if(i<n && str.charAt(i)=='-'){
	            ++i;
	            sign=-1;
	        }
	        //part 2 dealing with conversion
	        int num=0;
	        while(i<n && Character.isDigit(str.charAt(i))){
	            int digit=Character.getNumericValue(str.charAt(i));
	            if(num>maxDiv10 || (num==maxDiv10 && digit>Integer.MAX_VALUE%10)){
	                return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
	            }
	            num=num*10+digit;
	            i++;
	        }
	        return sign*num;
	        
	    }
	    
	    public boolean isNumber(String s) {
	        //leading/trailing white space?ignore; space between chars? not valid, "1 1"=>false;non numeric chars in the middle? false; +-sign? valid; non decimal numbers,like 0xff? false; exponent/scientific number,e.g 1e10? true
	        
	        //s1 dealing with leading whitespace
	        int i=0,n=s.length();
	        while(i<n && Character.isWhitespace(s.charAt(i))) i++;
	        //s2 dealing with +/-
	        if(i<n && (s.charAt(i)=='+'||s.charAt(i)=='-')) i++;
	        //s3 number: integer part;decimal point;fractional part
	        boolean isNumeric=false;
	        while(i<n && Character.isDigit(s.charAt(i))){//integer part
	            i++;
	            isNumeric=true;
	        }
	        if(i<n && s.charAt(i)=='.'){//decimal point and fractional part
	            i++;
	            while(i<n && Character.isDigit(s.charAt(i))){
	                i++;
	                isNumeric=true;
	            }
	        }
	        //s4 exponent/scientific number, remove s4 part if no need to consider this
	        if(isNumeric && i<n && s.charAt(i)=='e'){
	            i++;
	            isNumeric=false;
	            if(i<n && (s.charAt(i)=='+'||s.charAt(i)=='-')) i++;
	            while(i<n && Character.isDigit(s.charAt(i))){
	                i++;
	                isNumeric=true;
	            }
	        }
	        //s5 dealing with trailing whitespace
	        while(i<n && s.charAt(i)==' ') i++;
	        return isNumeric && i==n;
	     }
	    
	    
	 public boolean wordPattern(String pattern, String str) {
	   String[] strs=str.split(" ");
	   if(strs.length!=pattern.length()) return false;
	   Map<Object,Integer> indexMap=new HashMap<>();
	   for(int i=0;i<pattern.length();++i){
		   Object a=indexMap.put(pattern.charAt(i),i);
		   Object b=indexMap.put(strs[i],i);
		   if(!Objects.equals(a, b)) return false;
		  //if( !Objects.equals(indexMap.put(pattern.charAt(i),i),(indexMap.put(strs[i],i)))) return false;
		   //if (!Objects.equals(indexMap.put(pattern.charAt(i), i),indexMap.put(strs[i], i))) return false;
	   }
	   return true;
	 }
	
	    int[][] dp;

	    public void NumMatrix(int[][] matrix) {
	        if(matrix==null||matrix.length==0||matrix[0].length==0) return;
	        int m=matrix.length, n=matrix[0].length;
	        dp=new int[m+1][n+1];
	        for(int i=1;i<=m;++i){
	            for(int j=1;j<=n;++j){
	                dp[i][j]=dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]+matrix[i-1][j-1];
	            }
	        }
	    }

	    public int sumRegion(int row1, int col1, int row2, int col2) {
	        return dp[row2+1][col2+1]-dp[row2+1][col1]-dp[row1][col2+1]+dp[row1][col1];
	    }
	
	
	
	
    public static void main(String[] args) throws IOException{
    	Solution solution=new Solution();

       int[][] matrix={
    		   {1},
    		   {-1}
       };
       
       solution.NumMatrix(matrix);
       System.out.println(solution.sumRegion(0,0,0,0));
       System.out.println(solution.sumRegion(1,0,1,0));
       System.out.println(solution.sumRegion(0,0,1,0));

        
        
      //  System.out.println(solution. diffWaysToCompute("2*3-4*5"));
    	

    	

    	//solution.getSkyline(b);
    	//System.out.println(solution.permute(nums));
//    	System.out.println(solution.spiralOrder(b));

    	
    	/*
    	char[][] m={{'0','0','0','0','0'},
    			    {'0','1','1','1','0'},
    			    {'0','1','1','1','1'},
    			    {'0','0','0','0','0'}};
        
       System.out.println(solution.maximalRectangle(m));
       */
    	
    	//int[] A=new int[]{3,7};
        //solution.findPopularNum(A);
    	//System.out.println(solution.findSubArray(A, 10));
    	//System.out.println(solution.possibleSum(A,9));
    	
    	//113 path sum II
//    	TreeNode root=new TreeNode(1);
//    	solution.pathSum(root,1);
    	
    	//160 list intersection
//    	ListNode headA=new ListNode(1);
//    	ListNode headB=new ListNode(0);
//    	headA.next=new ListNode(2);
//    	headA.next.next=new ListNode(3);
//    	System.out.println(solution.getIntersectionNode(headA,headB));
    	
    	//87 Scramble string
    	//System.out.println(solution.isScramble("abc","acb"));
    	
    	//37 sudoku solver
    	/*
    	char[][] board={
    			{'5','3','.','.','7','.','.','.','.'},
    			{'6','.','.','1','9','5','.','.','.'},
    			{'.','9','8','.','.','.','.','6','.'},
    			{'8','.','.','.','6','.','.','.','3'},
       			{'4','.','.','8','.','3','.','.','1'},
       			{'7','.','.','.','2','.','.','.','6'},
       			{'.','6','.','.','.','.','2','8','.'},
       			{'.','.','.','4','1','9','.','.','5'},
       			{'.','.','.','.','8','.','.','7','9'},
    	};
    	solution.solve(board);
        for(int i=0;i<9;++i){
        	for(int j=0;j<9;++j){
        		System.out.print(board[i][j]);
        	}
        	System.out.println();
        }
        */
    	
    }


}