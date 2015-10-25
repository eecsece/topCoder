package test;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Random;

import test.Ideone.*;
public class IdeoneTest {
  public static int test(){
	  try{
		  System.out.println("try");
		  throw new Exception();
		  //return 1;
		//System.out.println("try");  
	  }catch(Exception e){
		  System.out.println("catch");
		  return 2;
	  }finally{
		  System.out.println("finally");
		  //return 3;
	  }
	 
  }
  
  public static void main(String[] args){
	  Ideone solution=new Ideone();
	  int[] height={4,3,2,1};
	  int[] front={0,1,2,3};
	  
	  Entry[] table=new Entry[3];
	  
	 
	  
	  
	  
	  //int[] height={5, 10, 15, 4, 13, 6, 3, 12};
	  //int[] front={0, 0, 0,  1, 1,  1, 2, 2};
	 // int[] height={6,5,	4,	3,	2,	1 };
	 // int[] front={0,	0,	0,	2,	2,	4};
	//  System.out.print(solution.restoreQueue(height,front));
	 // solution.restoreQueue2(height,front);
	  
//	  int[] A={2,0,1,0};
//	  System.out.println(solution.countPairs(A));
//	  System.out.println(test());
	  
	  //26 print binary tree in vertical order
	  /*
	  TreeNode root=new TreeNode(1);
	  root.left=new TreeNode(2);
	  root.right=new TreeNode(3);
	  root.left.left=new TreeNode(4);
	  root.left.right=new TreeNode(5);
	  root.right.left=new TreeNode(6);
	  root.right.right=new TreeNode(7);
	  root.right.left.right=new TreeNode(8);
	  root.right.right.right=new TreeNode(9);
	  solution.printVerticalOrder(root);
	  */
	  //25 swap zero and one
	  /*
	  int[] num={1,1,0,0,0};
	  Ideone.swapZeroOne(num);
	  for(int i:num){
		  System.out.print(i);
	  }
	  System.out.println();
	  */
	  
	  //20 pacific and atlantic
	  /*
	  int[][] matrix={
		  {1, 4, 4, 2},
		  {2, 1, 3, 1},
		  {2, 2, 2, 3},
		  {1, 2, 4, 2}
	  };
	  Ideone.validPoint(matrix);
	  */
	  
	  //19 given 2d matrix of 1 and 0, if a spot is 0, return # of 0s around it
	  /*
	  int[][] grid=new int[][]{
			  {1,0,1,0,1},
			  {1,0,0,1,1},
			  {1,0,0,0,1}
			  
	  };
	  System.out.println(solution.countZeros(grid, 0, 1));
	  for(int i=0;i<grid.length;++i){
		  for(int j=0;j<grid[0].length;++j){
			  System.out.print(grid[i][j]+" ");
		  }
		  System.out.println();
	  }*/
	  
	  /*18 leftSum of 2d matrix: {{1,2,3},{2,1,3},{3,1,2}} =>[[1 3 6] [3 8 12] [5 11 18]]
	  int[][] grid=new int[][]{
			  {1,2,3},
			  {2,3,1},
			  {2,1,3}
			  
	  };
	  int[][] res=Ideone.leftSumMatrix(grid);
	  for(int i=0;i<res.length;++i){
		  for(int j=0;j<res[0].length;++j){
			  System.out.print(res[i][j]+" ");
		  }
		  System.out.println();
	  }
	  */
	  
	  //16 union of two sorted arrays
	  //17 intersection of two sorted arrays
	  
	  //int[] a={1,2,4,5,6};
	  //int[] b={2,3,5,7};
//	  int[] a={1,2,3,4,4,};
//	  int[] b={2,3,4,4,5};
//	  System.out.println(Ideone.union2SortedArray(a, b));
//	  System.out.println(Ideone.arrayIntersection(a,b));
	  
	  
  }
}
