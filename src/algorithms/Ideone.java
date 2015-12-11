package algorithms;

import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;


class TreeNode2{
	int h;
	int f;
	int leftSize;
	TreeNode2 left;
	TreeNode2 right;
	TreeNode2(int height, int front){
		h=height;
		f=front;
	}
}
public class Ideone {
	//already know negatives >N/2,move negatives to front and return negatives count
	public int moveNeg(int[] nums){
		int i=0;
		for(int j=0;j<nums.length;++j){
			if(nums[j]<0){
				int temp=nums[i];
				nums[i++]=nums[j];
				nums[j]=temp;		
			}
		}
		return i;
	}
	
	//???readcell blob 10*10 matrix http://www.mitbbs.com/article_t/JobHunting/32989629.html
	public int getCell(int[][] m){
		int res=0;
		int i=0,j=0;
		if(isGood(i-1,j-1,m)) res|=1;
		if(isGood(i,j-1,m)) res|=2;
		if(isGood(i-1,j,m)) res|=4;
		if(isGood(i,j,m)) res|=8;
		return res;
		
	}
	private boolean isGood(int i,int j,int[][] m){
		if(i<0||j<0||i>=10||j>=10) return false;
		return m[i][j]==1;
	}
	
	//29 restore queue, height array does not contain duplicates
	//people line in a queue, each person write down how many people in front of him have larger heights
	//reshuffle the line and your task is to restore the line
	//e.g.[3,2,1],[0,1,1]=>3,1,2
	public List<Integer> restoreQueue(int[] height,int[] front){
		Map<Integer,Integer> map=new HashMap<>(); //map is used to associate front and sorted height
		for(int i=0;i<height.length;++i){
			map.put(height[i],front[i]);
		}
		Arrays.sort(height); //sort height in accending order, 
		//we need decending order, but Arrays.sort does not support reverse sort for primitive types
		//so I sort it in accending order and loop from right to left to get descending order
		List<Integer> res=new ArrayList<Integer>();
		for(int i=height.length-1;i>=0;--i){
			res.add(map.get(height[i]),height[i]); //insert in decending order
		}
		return res;
	}
	//another method is construct a BT and print inorder
	//o(nlgn)?:o(n)
	public void restoreQueue2(int[] height, int[] front){
		int n=height.length;
		TreeNode2[] nodes=new TreeNode2[n];
		for(int i=0;i<n;++i){
			nodes[i]=new TreeNode2(height[i],front[i]);
		}
		Comparator<TreeNode2> c =new Comparator<TreeNode2>(){
			public int compare(TreeNode2 n1,TreeNode2 n2){
				return n2.h-n1.h;
			}
		};
		Arrays.sort(nodes,c);
		TreeNode2 root=null;
		for(int i=0;i<n;++i){
			root=insert(nodes[i],root);
		}
		printInorder(root);
	}
    private TreeNode2 insert(TreeNode2 node, TreeNode2 root){
    	if(root==null) return node;
    	if(node.f>=root.leftSize+1){
    		node.f-=(root.leftSize+1);
    	    //node.leftSize+=root.leftSize+1;
    		root.right=insert(node,root.right);	
    		
    	}else{
    		root.leftSize++;
    		root.left=insert(node,root.left);	
    	}
    	return root;
    }
    private void printInorder(TreeNode2 root){
    	if(root==null) return;
    	printInorder(root.left);
    	System.out.print(root.h+" ");
    	printInorder(root.right);
    }
	
	//28 identical pairs: given int[] A, count how many pairs are identical
	//A=[2,0,1,0],return 1: [0,0]
	public int countPairs(int[] A){
		//map o(n):o(n)
		if(A==null||A.length==0) return 0;
		int count=0;
		Map<Integer,Integer> map=new HashMap<>();
		for(int a:A){
			int freq=map.containsKey(a)? map.get(a):0;
			map.put(a,freq+1);
		}
		for(int freq:map.values()){
			if(freq>1) count+=freq*(freq-1)/2;
		}
		return count;
		
		//bruteforce o(n^2):o(1)
		/*
		if(A==null||A.length==0) return 0;
		int count=0;
		for(int i=0;i<A.length-1;++i){
			for(int j=i+1;j<A.length;++j){
				if(A[i]==A[j]) count++;
			}
		}
		return count;
		*/
			
	}
	//27 count k-complemantary pair: given intk,int[] A; if A[i]+A[j]=k (i,j) is a pair
	//k=10, A=[1,5,9]=> return 3 (0,2),(2,0),(2,2)
	public int kComp(int[] A,int k){
		//method o(n):o(n)
		if(A==null||A.length==0) return 0;
		Map<Integer,Integer> map=new HashMap<>();
		int count=0;
		for(int a:A){
			int val=k-a;
			int freq=map.containsKey(val)? map.get(val):0;
			map.put(val,freq+1);
		}
		for(int a:A){
			count+=map.containsKey(a)? map.get(a):0;
		}
		return count;
		
		
		//method bruteforce o(n^2):o(1)
		/*
		if(A==null||A.length==0) return 0;
		int count=0;
		for(int i=0;i<A.length;++i){
			for(int j=i;j<A.length;++j){
				if(A[i]+A[j]==k){
					if (i!=j) count+=2;
					else count+=1;
				}
			}
		}
		return count;
		*/
	}
	
	
	//26 print binary tree in vertical order
	//TreeMap+preorder traversal
	public void printVerticalOrder(TreeNode root){
		if(root==null) return;
		Map<Integer,List<Integer>> map=new TreeMap<>();
		getVerticalOrder(root,0,map);
		for(Integer key:map.keySet()){
			System.out.println(map.get(key));
		}
	}
	private void getVerticalOrder(TreeNode root, int hd, Map<Integer,List<Integer>> map){
		//hd is horizontal distance
		if(root==null) return;
		if(map.containsKey(hd)){
		  map.get(hd).add(root.val);
		}else{
			List<Integer> list=new ArrayList<>();
			list.add(root.val);
			map.put(hd, list);
		}
		getVerticalOrder(root.left,hd-1,map);
		getVerticalOrder(root.right,hd+1,map);	
	}
	
	//25 swap zero and one
	public static void swapZeroOne(int[] num){
		int i=0,j=num.length-1;
		while(i<j){
			if(num[i]==0){
				i++;
			}else{
				int temp=num[i];
				num[i]=num[j];
				num[j]=temp;
				--j;
			}
		}
	}
	
	
	//20 pacific and atlantic
	public static void validPoint(int[][] m){
		if(m==null||m.length==0||m[0].length==0) return;
		boolean[][] resPacific=new boolean[m.length][m[0].length];
		boolean[][] resAtlantic=new boolean[m.length][m[0].length];
		for(int i=0;i<m.length;++i){
			dfsPacific(resPacific,m,i,0);
			dfsAtlantic(resAtlantic,m,i,m[0].length-1);
		}
		for(int j=1;j<m[0].length;++j){
			dfsPacific(resPacific,m,0,j);
			dfsAtlantic(resAtlantic,m,m.length-1,j);
		}
		
		for(int i=0;i<m.length;++i){
			for(int j=0;j<m.length;++j){
				if(resPacific[i][j]&&resAtlantic[i][j]) 
					System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}	
	}
	private static void dfsPacific(boolean[][] res,int[][] m, int i,int j){
		if(i<0||i>=m.length||j<0||j>=m[0].length||res[i][j]) return;
		res[i][j]=true;
		if(i<m.length-1 && m[i][j]<=m[i+1][j]){
			dfsPacific(res,m,i+1,j);
		}
		if(j<m[0].length-1 && m[i][j]<=m[i][j+1]){
			dfsPacific(res,m,i,j+1);
		}
//		if(j<m[0].length-1 &&i<m.length-1 && m[i][j]<=m[i+1][j+1]){
//			dfsPacific(res,m,i+1,j+1);
//		}
	}
	private static void dfsAtlantic(boolean[][] res,int[][] m, int i,int j){
		if(i<0||i>=m.length||j<0||j>=m[0].length||res[i][j]) return;
		res[i][j]=true;
		if(i>0 && m[i][j]<=m[i-1][j]){
			dfsAtlantic(res,m,i-1,j);
		}
		if(j>0 && m[i][j]<=m[i][j-1]){
			dfsAtlantic(res,m,i,j-1);
		}
//		if(j>0 &&i>0 && m[i][j]<=m[i-1][j-1]){
//			dfsAtlantic(res,m,i-1,j-1);
//		}
	}
	
	//19 given 2d matrix of 1 and 0, if a spot is 0, return # of 0s around it
    private int count=0;
	public int countZeros(int[][] matrix, int i,int j){
		if(matrix[i][j]==0){
			dfs(matrix,i,j);
		}
		for(i=0;i<matrix.length;++i){
			for(j=0;j<matrix[0].length;++j){
				if(matrix[i][j]==2) matrix[i][j]=0;
			}
		}
		return count;
	}
	
	private void dfs(int[][] matrix,int i,int j){
		if(i<0||i>=matrix.length || j<0||j>=matrix[0].length||matrix[i][j]!=0) return;
		count++;
		matrix[i][j]=2;
		dfs(matrix,i-1,j);
		dfs(matrix,i+1,j);
		dfs(matrix,i,j-1);
		dfs(matrix,i,j+1);
		//matrix[i][j]=0;cannot recover matrix here, count will be wrong
	}
	//18 leftSum of 2d matrix: {{1,2,3},{2,1,3},{3,1,2}} =>[[1 3 6] [3 8 12] [5 11 18]]
	public static int[][] leftSumMatrix(int[][] grid){
		//res[i][j]=res[i-1][j]+sum(grid[i][0]+...+grid[i][j])
		int m=grid.length, n=grid[0].length;
		int[][] res=new int[m][n];		
		for(int i=0;i<m;++i){
			int[] rowSum=new int[n];
			for(int j=0;j<n;++j){
				rowSum[j]= j>0? rowSum[j-1]+grid[i][j]:grid[i][j];
				res[i][j]= i>0? res[i-1][j]+rowSum[j]:rowSum[j];
			}
		}
		return res;
	}
	//17 intersection of two sorted arays {1,2,3,3,4}, {2,3,4,4,5} => {2,3,4}
	  public static ArrayList<Integer> arrayIntersection(int [] a, int[] b)
	  {
	      int len_a=a.length;
	      int len_b=b.length;
	      int i=0;
	      int j=0;
	      ArrayList<Integer> alist=new ArrayList<>();
	      while(i<len_a && j<len_b)
	      {
	          if(a[i]<b[j])
	              i++;
	          else if(a[i]>b[j])
	              j++;
	          else //a[i]==b[j]
	          {
	              alist.add(a[i]);
	              do{i++;}while(i<len_a && a[i]==a[i-1]);//if a/b is gurantee no duplicates in itself, use i++/j++
	              do{j++;}while(j<len_b && b[j]==b[j-1]);
	          }
	      }
	     return alist;    
	    }
	//16 union of two sorted array {1,2,3,3,4}, {2,3,4,4,5} => {1,2,3,4,5}
	public static ArrayList<Integer> union2SortedArray(int[] a,int[] b){
		  int len_a=a.length;
	      int len_b=b.length;
	      int i=0;
	      int j=0;
	      ArrayList<Integer> alist=new ArrayList<>();
	      while(i<len_a && j<len_b)
	      {
	          if(a[i]<b[j]){
	        	  alist.add(a[i++]);
	        	  //while(i<len_a && a[i]==a[i-1]){i++;}; //handle duplicates in each array
	          }
	          else if(a[i]>b[j]){
	        	  alist.add(b[j++]);
	        	//while(j<len_b && b[j]==b[j-1]){j++;};
	          }
	          else //a[i]==b[j]
	          {
	              alist.add(a[i++]);
	              j++;
	              //while(j<len_b && b[j]==b[j-1]){j++;};
	              //while(j<len_b && b[j]==b[j-1]){j++;};
	          }
	      }
	      while(i<len_a){
	    	  alist.add(a[i++]);
	      }
	      while(j<len_b){
	    	  alist.add(b[j++]);
	      }
	     return alist;    
	}
	//15 sort hash map by val then key
	public void getFreq(String s){
		  if(s==null||s.length()==0) return;
		  Map<Character,Integer> map=new HashMap<Character,Integer>();
		  for(int i=0;i<s.length();++i){
			  char c=s.charAt(i);
			  if(map.containsKey(c)){
				  map.put(c,map.get(c)+1);
			  }else{
				  map.put(c, 1);
			  }
		  }
		  printByVal(map);
	  }

	  private void printByVal(Map<Character,Integer> map){
		  Comparator<Entry<Character,Integer>> c=new Comparator<Entry<Character,Integer>>(){
				 @Override
				 public int compare(Entry<Character,Integer> e1,Entry<Character,Integer> e2){
				 	 //if val equals, compare key
					 if(e1.getValue()==e2.getValue()) return e1.getKey()-e2.getKey();
					 return e2.getValue()-e1.getValue();
				 }
		  };
		  List<Entry<Character,Integer>> list=new ArrayList<>(map.entrySet());
		  Collections.sort(list,c);

		  //if need restore to map, use linkedHashMap
		  Map<Character,Integer> sortedMap=new LinkedHashMap<>();

		  for(Entry e:list){
			 System.out.println(e.getValue()+" "+e.getKey());
			 sortedMap.put((Character)e.getKey(), (Integer)e.getValue());
		 }

		  //another way, use sortedset instead of list, not good to convert back to map
		  SortedSet<Entry<Character,Integer>> set=new TreeSet<Entry<Character,Integer>>(c);
		  set.addAll(map.entrySet());
		  System.out.print(set);
		  

	  }
	
	
}
